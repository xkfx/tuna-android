package com.example.tuna.hui.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CardListVO {

    private List<Card> cardList;

    private int totalNumberOfPages;

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    @NotNull
    @Override
    public String toString() {
        return "CardListVO{" +
                "cardList=" + cardList +
                ", totalNumberOfPages=" + totalNumberOfPages +
                '}';
    }
}
