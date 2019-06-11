package com.example.activity_maim.VO;

import java.io.Serializable;

public class GameVO implements Serializable {
    private String name;
    private String contents;
    private String category;
    private Long id;
    private Long currentPrice;
    private Long goalPrice;
    private String profileImage;
    private Boolean success;
    private String gameInformation, investmentCondition, investmentInformation, companyIntroduction;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Long currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getGoalPrice() {
        return goalPrice;
    }

    public void setGoalPrice(Long goalPrice) {
        this.goalPrice = goalPrice;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getGameInformation() {
        return gameInformation;
    }

    public void setGameInformation(String gameInformation) {
        this.gameInformation = gameInformation;
    }

    public String getInvestmentCondition() {
        return investmentCondition;
    }

    public void setInvestmentCondition(String investmentCondition) {
        this.investmentCondition = investmentCondition;
    }

    public String getInvestmentInformation() {
        return investmentInformation;
    }

    public void setInvestmentInformation(String investmentInformation) {
        this.investmentInformation = investmentInformation;
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
