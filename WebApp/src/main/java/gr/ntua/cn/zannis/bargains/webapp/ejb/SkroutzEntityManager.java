package gr.ntua.cn.zannis.bargains.webapp.ejb;

import gr.ntua.cn.zannis.bargains.webapp.persistence.SkroutzEntity;
import gr.ntua.cn.zannis.bargains.webapp.persistence.entities.Category;
import gr.ntua.cn.zannis.bargains.webapp.persistence.entities.Product;
import gr.ntua.cn.zannis.bargains.webapp.persistence.entities.Shop;
import gr.ntua.cn.zannis.bargains.webapp.persistence.entities.Sku;
import gr.ntua.cn.zannis.bargains.webapp.rest.impl.SkroutzRestClient;
import gr.ntua.cn.zannis.bargains.webapp.ui.components.Notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zannis <zannis.kal@gmail.com>
 */
@Stateless
@LocalBean
public class SkroutzEntityManager {

    private static final Logger log = LoggerFactory.getLogger(SkroutzEntityManager.class);

    @PersistenceContext
    private EntityManager em;

    public <T extends SkroutzEntity> T persist(T object) throws RuntimeException {
        try {
            em.persist(object);
            log.info("Object " + object.toString() + " persisted successfully.");
        } catch (Exception e) {
            Notifier.error("Υπήρξε πρόβλημα στο persist στο " + object.toString(), e);
        }
        return object;
    }

    public <T extends SkroutzEntity> T merge(T object) throws RuntimeException {
        try {
            em.merge(object);
            log.info("Object " + object.toString() + " merged successfully.");
        } catch (Exception e) {
            Notifier.error("Υπήρξε πρόβλημα στο merge στο " + object.toString(), e);
        }
        return object;
    }

    public <T extends SkroutzEntity> void remove(T object) throws RuntimeException {
        try {
            em.remove(object);
            log.info("Object " + object + " removed successfully.");
        } catch (Exception e) {
            Notifier.error("Υπήρξε πρόβλημα στο remove στο " + object.toString(), e);
        }
    }

    public <T extends SkroutzEntity> T find(Class<T> tClass, Object skroutzId) throws RuntimeException {
        T result;
        // finds an object by its skroutzId
        TypedQuery<T> q = em.createNamedQuery(tClass.getSimpleName() + ".findBySkroutzId", tClass);
        q.setParameter("skroutzId", skroutzId);
        try {
            result = q.getSingleResult();
        } catch (NoResultException e) {
            // swallow and return null
            result = null;
        } catch (Exception e) {
            Notifier.error("Υπήρξε πρόβλημα στο find" + tClass.getSimpleName() + " με skroutzId " + skroutzId, e);
            result = null;
        }
        return result;
    }

    public <T extends SkroutzEntity> List<T> findAll(Class<T> tClass) throws RuntimeException {
        List<T> result;
        TypedQuery<T> q = createNamedQuery(tClass.getSimpleName() + ".findAll", tClass);
        try {
            result = q.getResultList();
        } catch (Exception e) {
            Notifier.error("Υπήρξε πρόβλημα στο findAll" + tClass.getSimpleName(), e);
            result = new ArrayList<>();
        }
        return result;
    }

    public <T extends SkroutzEntity> TypedQuery<T> createNamedQuery(String namedQuery, Class<T> tClass) {
        return em.createNamedQuery(namedQuery, tClass);
    }

    public <T extends SkroutzEntity> void persistOrMerge(Class<T> tClass, List<T> objects) {
        for (T object : objects) {
            persistOrMerge(tClass, object);
        }
    }

    public <T extends SkroutzEntity> T persistOrMerge(Class<T> tClass, T transientObject) {
        T persistentObject = find(tClass, transientObject.getSkroutzId());
        if (persistentObject == null) {
            if (transientObject.getClass().isAssignableFrom(Product.class)) {
                // the transient object contains the fields shop_id, category_id, sku_id
                // first persist all its dependencies (shop, category). sku must have been persisted before this call
                Product transientProduct = (Product) transientObject;
                Shop shop = SkroutzRestClient.getInstance().get(Shop.class, transientProduct.getShopId());
                persistOrMerge(Shop.class, shop);
                Category category = SkroutzRestClient.getInstance().get(Category.class, transientProduct.getCategoryId());
                persistOrMerge(Category.class, category);
            } else if (transientObject.getClass().isAssignableFrom(Sku.class)) {
                Sku transientSku = (Sku) transientObject;
                Category category = SkroutzRestClient.getInstance().get(Category.class, transientSku.getCategoryId());
                persistOrMerge(Category.class, category);
            } else if (transientObject.getClass().isAssignableFrom(Category.class)) {
                Category transientCategory = (Category) transientObject;
                Category parent = SkroutzRestClient.getInstance().get(Category.class, transientCategory.getParentId());
                persistOrMerge(Category.class, parent);
            }
            persistentObject = persist(transientObject);
        } else {
            persistentObject.updateFrom(transientObject);
            merge(persistentObject);
        }
        return persistentObject;
    }
}