package com.example.a123.my_cook_book.models;

import com.example.a123.my_cook_book.modelsDb.*;

import java.util.List;

public interface DbHelper {

    List<Receipts> getAllReceipts();

    Boolean isReceiptsEmpty();

    Long saveReceipts(Receipts receipt);

    Receipts getReceipt(Long Key);

    Boolean saveReceiptsList(List<Receipts> receiptsList);

    List<ReceiptsSteps> getReceiptsSteps(Long key);

    Boolean saveReceiptsSteps(List<ReceiptsSteps> receiptsStepsList);

    Boolean saveReceiptsSteps(ReceiptsSteps receiptsStepsList);

}
