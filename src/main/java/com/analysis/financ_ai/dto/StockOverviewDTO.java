package com.analysis.financ_ai.dto;

import com.analysis.financ_ai.model.StockOverview;
import com.analysis.financ_ai.model.StockOverview.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class StockOverviewDTO {

    @JsonProperty("Symbol")
    String symbol;

    @JsonProperty("Name")
    String name;

    @JsonProperty("Description")
    String description;

    @JsonProperty("AssetType")
    String assetType;

    @JsonProperty("CIK")
    String cik;

    @JsonProperty("Exchange")
    String exchange;

    @JsonProperty("Currency")
    String currency;

    @JsonProperty("Country")
    String country;

    @JsonProperty("Sector")
    String sector;

    @JsonProperty("Industry")
    String industry;

    @JsonProperty("Address")
    String address;

    @JsonProperty("OfficialSite")
    String officialSite;

    @JsonProperty("FiscalYearEnd")
    String fiscalYearEnd;

    @JsonProperty("LatestQuarter")
    String latestQuarter;

    @JsonProperty("MarketCapitalization")
    String marketCapitalization;

    @JsonProperty("EBITDA")
    String ebitda;

    @JsonProperty("BookValue")
    String bookValue;

    @JsonProperty("EPS")
    String eps;

    @JsonProperty("DilutedEPSTTM")
    String dilutedEps;

    @JsonProperty("RevenuePerShareTTM")
    String revenuePerShare;

    @JsonProperty("RevenueTTM")
    String revenueTtm;

    @JsonProperty("GrossProfitTTM")
    String grossProfitTtm;

    @JsonProperty("ProfitMargin")
    String profitMargin;

    @JsonProperty("OperatingMarginTTM")
    String operatingMargin;

    @JsonProperty("ReturnOnAssetsTTM")
    String returnOnAssets;

    @JsonProperty("ReturnOnEquityTTM")
    String returnOnEquity;

    @JsonProperty("QuarterlyEarningsGrowthYOY")
    String earningsGrowth;

    @JsonProperty("QuarterlyRevenueGrowthYOY")
    String revenueGrowth;

    @JsonProperty("PERatio")
    String peRatio;

    @JsonProperty("PEGRatio")
    String pegRatio;

    @JsonProperty("PriceToBookRatio")
    String priceToBookRatio;

    @JsonProperty("PriceToSalesRatioTTM")
    String priceToSalesRatio;

    @JsonProperty("EVToRevenue")
    String evToRevenue;

    @JsonProperty("EVToEBITDA")
    String evToEbitda;

    @JsonProperty("Beta")
    String beta;

    @JsonProperty("52WeekHigh")
    String maxPriceYear;

    @JsonProperty("52WeekLow")
    String minPriceYear;

    @JsonProperty("50DayMovingAverage")
    String movingAverage50Day;

    @JsonProperty("200DayMovingAverage")
    String movingAverage200Day;

    @JsonProperty("SharesOutstanding")
    String sharesOutstanding;

    @JsonProperty("AnalystTargetPrice")
    String analystTargetPrice;

    @JsonProperty("AnalystRatingStrongBuy")
    String analystRatingStrongBuy;

    @JsonProperty("AnalystRatingBuy")
    String analystRatingBuy;

    @JsonProperty("AnalystRatingHold")
    String analystRatingHold;

    @JsonProperty("AnalystRatingSell")
    String analystRatingSell;

    @JsonProperty("AnalystRatingStrongSell")
    String analystRatingStrongSell;

    @JsonProperty("TrailingPE")
    String trailingPe;

    @JsonProperty("ForwardPE")
    String forwardPe;

    @JsonProperty("DividendPerShare")
    String dividendPerShare;

    @JsonProperty("DividendYield")
    String dividendYield;

    @JsonProperty("DividendDate")
    String dividendDate;

    @JsonProperty("ExDividendDate")
    String exDividendDate;

    public StockOverview toModel() {
        return StockOverview.builder()
                .symbol(symbol)
                .name(name)
                .description(description)
                .companyInfo(CompanyInfo.builder()
                        .assetType(assetType)
                        .cik(cik)
                        .exchange(exchange)
                        .currency(currency)
                        .country(country)
                        .sector(sector)
                        .industry(industry)
                        .address(address)
                        .officialSite(officialSite)
                        .fiscalYearEnd(fiscalYearEnd)
                        .latestQuarter(latestQuarter)
                        .build())
                .financialMetrics(FinancialMetrics.builder()
                        .marketCapitalization(marketCapitalization)
                        .ebitda(ebitda)
                        .bookValue(bookValue)
                        .eps(eps)
                        .dilutedEps(dilutedEps)
                        .revenuePerShare(revenuePerShare)
                        .revenueTtm(revenueTtm)
                        .grossProfitTtm(grossProfitTtm)
                        .profitMargin(profitMargin)
                        .operatingMargin(operatingMargin)
                        .returnOnAssets(returnOnAssets)
                        .returnOnEquity(returnOnEquity)
                        .earningsGrowth(earningsGrowth)
                        .revenueGrowth(revenueGrowth)
                        .build())
                .marketData(MarketData.builder()
                        .peRatio(peRatio)
                        .pegRatio(pegRatio)
                        .priceToBookRatio(priceToBookRatio)
                        .priceToSalesRatio(priceToSalesRatio)
                        .evToRevenue(evToRevenue)
                        .evToEbitda(evToEbitda)
                        .beta(beta)
                        .maxPriceYear(maxPriceYear)
                        .minPriceYear(minPriceYear)
                        .movingAverage50Day(movingAverage50Day)
                        .movingAverage200Day(movingAverage200Day)
                        .sharesOutstanding(sharesOutstanding)
                        .build())
                .analystData(AnalystData.builder()
                        .analystTargetPrice(analystTargetPrice)
                        .analystRatingStrongBuy(analystRatingStrongBuy)
                        .analystRatingBuy(analystRatingBuy)
                        .analystRatingHold(analystRatingHold)
                        .analystRatingSell(analystRatingSell)
                        .analystRatingStrongSell(analystRatingStrongSell)
                        .trailingPe(trailingPe)
                        .forwardPe(forwardPe)
                        .build())
                .dividendInfo(DividendInfo.builder()
                        .dividendPerShare(dividendPerShare)
                        .dividendYield(dividendYield)
                        .dividendDate(dividendDate)
                        .exDividendDate(exDividendDate)
                        .build())
                .build();
    }
}
