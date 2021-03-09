public class Products {
    private final Long productId;
    private final Long sellerId;
    private final String oriMinPrice;
    private final String oriMaxPrice;
    private final Long promotionId;
    private final Long startTime;
    private final Long endTime;
    private final int phase;
    private final String productTitle;
    private final String minPrice;
    private final String maxPrice;
    private final String discount;
    private final String totalStock;
    private final String stock;
    private final String orders;
    private final boolean soldout;
    private final String productImage;
    private final String productDetailUrl;
    private final String totalTranpro3;
    private final String productPositiveRate;
    private final String productAverageStar;
    private final int itemEvalTotalNum;
    private final String icon;

    public Products(Long productId, Long sellerId, String oriMinPrice, String oriMaxPrice, Long promotionId,
                    Long startTime, Long endTime, int phase, String productTitle, String minPrice, String maxPrice,
                    String discount, String totalStock, String stock, String orders, boolean soldout, String productImage,
                    String productDetailUrl, String totalTranpro3, String productPositiveRate, String productAverageStar,
                    int itemEvalTotalNum, String icon) {

        this.productId = productId;
        this.sellerId = sellerId;
        this.oriMinPrice = oriMinPrice;
        this.oriMaxPrice = oriMaxPrice;
        this.promotionId = promotionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.phase = phase;
        this.productTitle = productTitle;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.discount = discount;
        this.totalStock = totalStock;
        this.stock = stock;
        this.orders = orders;
        this.soldout = soldout;
        this.productImage = productImage;
        this.productDetailUrl = productDetailUrl;
        this.totalTranpro3 = totalTranpro3;
        this.productPositiveRate = productPositiveRate;
        this.productAverageStar = productAverageStar;
        this.itemEvalTotalNum = itemEvalTotalNum;
        this.icon = icon;
    }

    public String getProductId() {
        return String.valueOf(productId);
    }

    public String getSellerId() {
        return String.valueOf(sellerId);
    }

    public String getOriMinPrice() {
        return oriMinPrice;
    }

    public String getOriMaxPrice() {
        return oriMaxPrice;
    }

    public String getPromotionId() {
        return String.valueOf(promotionId);
    }

    public String getStartTime() {
        return String.valueOf(startTime);
    }

    public String getEndTime() {
        return String.valueOf(endTime);
    }

    public String getPhase() {
        return String.valueOf(phase);
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public String getTotalStock() {
        return totalStock;
    }

    public String getStock() {
        return stock;
    }

    public String getOrders() {
        return orders;
    }

    public String isSoldout() {
        return String.valueOf(soldout);
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductDetailUrl() {
        return productDetailUrl;
    }

    public String getTotalTranpro3() {
        return totalTranpro3;
    }

    public String getProductPositiveRate() {
        return productPositiveRate;
    }

    public String getProductAverageStar() {
        return productAverageStar;
    }

    public String getItemEvalTotalNum() {
        return String.valueOf(itemEvalTotalNum);
    }

    public String getIcon() {
        return icon;
    }
}
