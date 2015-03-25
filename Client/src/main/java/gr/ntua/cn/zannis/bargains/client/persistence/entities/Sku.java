package gr.ntua.cn.zannis.bargains.client.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.ntua.cn.zannis.bargains.client.persistence.SkroutzEntity;

import java.util.List;

/**
 * The persistent class for a Stock Keeping Unit which practically is
 * a collection of products.
 * @author zannis <zannis.kal@gmail.com
 */
public class Sku extends SkroutzEntity {

    protected static final long serialVersionUID = -1L;

    private long id;
    private String ean;
    private String pn;
    private String name;
    private String displayName;
    private long categoryId;
    private String firstProductShopInfo;
    private String clickUrl;
    private float priceMax;
    private float priceMin;
    private float reviewScore;
    private int shopCount;
    private String plainSpecSummary;
    private long manufacturerId;
    private boolean future;
    private int reviewsCount;
    private boolean virtual;
    private Images images;

    public Sku(@JsonProperty("id") long id,
               @JsonProperty("ean") String ean,
               @JsonProperty("pn") String pn,
               @JsonProperty("name") String name,
               @JsonProperty("display_name") String displayName,
               @JsonProperty("category_id") long categoryId,
               @JsonProperty("first_product_shop_info") String firstProductShopInfo,
               @JsonProperty("click_url") String clickUrl,
               @JsonProperty("price_max") float priceMax,
               @JsonProperty("price_min") float priceMin,
               @JsonProperty("review_score") float reviewScore,
               @JsonProperty("shop_count") int shopCount,
               @JsonProperty("plain_spec_summary") String plainSpecSummary,
               @JsonProperty("manufacturer_id") long manufacturerId,
               @JsonProperty("future") boolean future,
               @JsonProperty("reviews_count") int reviewsCount,
               @JsonProperty("virtual") boolean virtual,
               @JsonProperty("images") Images images) {
        super();
        this.skroutzId = id;
        this.ean = ean;
        this.pn = pn;
        this.name = name;
        this.displayName = displayName;
        this.categoryId = categoryId;
        this.firstProductShopInfo = firstProductShopInfo;
        this.clickUrl = clickUrl;
        this.priceMax = priceMax;
        this.priceMin = priceMin;
        this.reviewScore = reviewScore;
        this.shopCount = shopCount;
        this.plainSpecSummary = plainSpecSummary;
        this.manufacturerId = manufacturerId;
        this.future = future;
        this.reviewsCount = reviewsCount;
        this.virtual = virtual;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getFirstProductShopInfo() {
        return firstProductShopInfo;
    }

    public void setFirstProductShopInfo(String firstProductShopInfo) {
        this.firstProductShopInfo = firstProductShopInfo;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public float getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(float priceMax) {
        this.priceMax = priceMax;
    }

    public float getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(float priceMin) {
        this.priceMin = priceMin;
    }

    public float getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(float reviewScore) {
        this.reviewScore = reviewScore;
    }

    public int getShopCount() {
        return shopCount;
    }

    public void setShopCount(int shopCount) {
        this.shopCount = shopCount;
    }

    public String getPlainSpecSummary() {
        return plainSpecSummary;
    }

    public void setPlainSpecSummary(String plainSpecSummary) {
        this.plainSpecSummary = plainSpecSummary;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public boolean isFuture() {
        return future;
    }

    public void setFuture(boolean future) {
        this.future = future;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public static class Images {
        String main;
        List<String> alternatives;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public List<String> getAlternatives() {
            return alternatives;
        }

        public void setAlternatives(List<String> alternatives) {
            this.alternatives = alternatives;
        }
    }
}