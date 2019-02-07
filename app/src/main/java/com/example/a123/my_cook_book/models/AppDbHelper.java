package com.example.a123.my_cook_book.models;

import com.example.a123.my_cook_book.modelsDb.*;
import com.example.a123.my_cook_book.modelsDb.DaoSession;
import com.example.a123.my_cook_book.modelsDb.Receipts;

import java.util.List;

import javax.inject.Inject;

public class AppDbHelper implements DbHelper{

    private final DaoSession mDaoSession;


    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public List<Receipts> getAllReceipts() {
        return mDaoSession.getReceiptsDao().loadAll();

    }

    @Override
    public Boolean isReceiptsEmpty() {
        return !(mDaoSession.getReceiptsDao().count() > 0);
    }

    @Override
    public Long saveReceipts(final Receipts receipt) {
        long id = mDaoSession.getReceiptsDao().insert(receipt);
        return id;
    }

    @Override
    public Boolean saveReceiptsList(final List<Receipts> receiptList) {
        mDaoSession.getReceiptsDao().insertInTx(receiptList);

        return true;
    }

    public Receipts getReceipt(Long Key) {
        return mDaoSession.getReceiptsDao().load(Key);
    }

    public Boolean saveReceiptsSteps(ReceiptsSteps stepToReceipts) {
        mDaoSession.getReceiptsStepsDao().insert(stepToReceipts);
        return true;
    }

    public List<ReceiptsSteps> getReceiptsSteps(Long key) {
        return mDaoSession.getReceiptsStepsDao()._queryReceipts_MListStep(key);
    }

    @Override
    public Boolean saveReceiptsSteps(List<ReceiptsSteps> receiptsStepsList) {
        mDaoSession.getReceiptsStepsDao().insertInTx(receiptsStepsList);
        return true;
    }

    public void deleteReceipt(Receipts receipt, List<ReceiptsSteps> list) {
        Long key = receipt.getId_receipts();
//        deletePicture(receipt.getIcon());
//        deletePicture(receipt.getImageMain());
        mDaoSession.getReceiptsDao().deleteByKey(key);
//        mDaoSession.getStepToReceiptsDao().deleteInTx(list);
        mDaoSession.getReceiptsStepsDao().deleteInTx(receipt.getMListStep());
    }

}
