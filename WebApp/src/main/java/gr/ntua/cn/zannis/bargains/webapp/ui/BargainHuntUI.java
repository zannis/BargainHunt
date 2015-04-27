package gr.ntua.cn.zannis.bargains.webapp.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import gr.ntua.cn.zannis.bargains.webapp.ejb.dao.RequestDAO;
import gr.ntua.cn.zannis.bargains.webapp.ejb.dao.impl.RequestDAOImpl;
import gr.ntua.cn.zannis.bargains.webapp.ejb.impl.SkroutzEntityManager;
import gr.ntua.cn.zannis.bargains.webapp.ui.screens.BargainView;
import gr.ntua.cn.zannis.bargains.webapp.ui.screens.MainView;
import gr.ntua.cn.zannis.bargains.webapp.ui.screens.ProductsView;
import gr.ntua.cn.zannis.bargains.webapp.ui.screens.SearchView;

import javax.inject.Inject;
import java.util.Locale;

/**
 *
 */
@CDIUI("")
@Theme("bargainhunt")
@Widgetset("gr.ntua.cn.zannis.bargains.webapp.BargainHuntWidgetset")
public class BargainHuntUI extends UI {

    @Inject
    SkroutzEntityManager skroutzEm;

    @Inject
    RequestDAOImpl requests;

    @Inject
    CDIViewProvider viewProvider;

    /* Internationalization
    ResourceBundle i18nBundle;
    public static ResourceBundle MESSAGES = ResourceBundle.getBundle(, Locale.getDefault()); */
    private Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
//        final ResourceBundle i18n = ResourceBundle.getBundle("gr.ntua.cn.zannis.bargains.webapp.i18n.Messages", getLocale());

        getPage().setTitle("BargainHunt");
        initNavigator();
    }

    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
//        i18nBundle = ResourceBundle.getBundle("gr.ntua.cn.zannis.bargains.webapp.i18n.Messages", getLocale());
    }

    private void initNavigator() {
        navigator = new Navigator(this, this);
        navigator.addProvider(viewProvider);
        navigator.addView(MainView.NAME, MainView.class);
        navigator.addView(SearchView.NAME, SearchView.class);
        navigator.addView(ProductsView.NAME, ProductsView.class);
        navigator.addView(BargainView.NAME, BargainView.class);
        navigator.navigateTo(getNavigator().getState());
    }

    @Override
    public Navigator getNavigator() {
        return navigator;
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public SkroutzEntityManager getSkroutzEm() {
        return skroutzEm;
    }

    public RequestDAO getRequests() {
        return requests;
    }
//
//    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = BargainHuntUI.class, productionMode = false)
//    public static class MyUIServlet extends VaadinServlet {
//    }
}
