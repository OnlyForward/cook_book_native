package com.example.a123.my_cook_book.modelsDb;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity(active = true, nameInDb = "Receipts")
public class Receipts {

    @Id
    private Long id_receipts;


    @NotNull
    @Property(nameInDb = "description")
    private String Description;

    @NotNull
    @Property(nameInDb = "title")
    private String Title;

    @NotNull
    @Property(nameInDb = "icon")
    private String Icon;

    @NotNull
    @Property(nameInDb = "image_main")
    private String ImageMain;


    @NotNull
    @Property(nameInDb = "ingredients")
    private String Ingredients;

    @ToMany(joinProperties = {@JoinProperty(name = "id_receipts", referencedName = "id_receipts_step")})
    private List<ReceiptsSteps> mListStep;


    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;


    /** Used for active entity operations. */
    @Generated(hash = 703076825)
    private transient ReceiptsDao myDao;

    @Generated(hash = 874512405)
    public Receipts(Long id_receipts, @NotNull String Description, @NotNull String Title,
            @NotNull String Icon, @NotNull String ImageMain, @NotNull String Ingredients) {
        this.id_receipts = id_receipts;
        this.Description = Description;
        this.Title = Title;
        this.Icon = Icon;
        this.ImageMain = ImageMain;
        this.Ingredients = Ingredients;
    }

    @Generated(hash = 1794552830)
    public Receipts() {
    }

    public Long getId_receipts() {
        return this.id_receipts;
    }

    public void setId_receipts(Long id_receipts) {
        this.id_receipts = id_receipts;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getIcon() {
        return this.Icon;
    }

    public void setIcon(String Icon) {
        this.Icon = Icon;
    }

    public String getImageMain() {
        return this.ImageMain;
    }

    public void setImageMain(String ImageMain) {
        this.ImageMain = ImageMain;
    }

    public String getIngredients() {
        return this.Ingredients;
    }

    public void setIngredients(String Ingredients) {
        this.Ingredients = Ingredients;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1978093059)
    public List<ReceiptsSteps> getMListStep() {
        if (mListStep == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ReceiptsStepsDao targetDao = daoSession.getReceiptsStepsDao();
            List<ReceiptsSteps> mListStepNew = targetDao._queryReceipts_MListStep(id_receipts);
            synchronized (this) {
                if (mListStep == null) {
                    mListStep = mListStepNew;
                }
            }
        }
        return mListStep;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1141418610)
    public synchronized void resetMListStep() {
        mListStep = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1225170103)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getReceiptsDao() : null;
    }

}
