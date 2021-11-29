package com.qx.eis.domain;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/4 10:14
 */
public class EisEisDomain {
    //情绪知觉
    private int qxzj=0;
    //自我情绪管理
    private int zwqx=0;
    //他人情绪管理
    private int trqx=0;
    //情绪表达
    private int qxbd=0;

    private int sum;

    private EisUser eisUser;

    public int getQxzj() {
        return qxzj;
    }

    public void setQxzj(int qxzj) {
        this.qxzj = qxzj;
    }

    public int getZwqx() {
        return zwqx;
    }

    public void setZwqx(int zwqx) {
        this.zwqx = zwqx;
    }

    public int getTrqx() {
        return trqx;
    }

    public void setTrqx(int trqx) {
        this.trqx = trqx;
    }

    public int getQxbd() {
        return qxbd;
    }

    public void setQxbd(int qxbd) {
        this.qxbd = qxbd;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public EisUser getEisUser() {
        return eisUser;
    }

    public void setEisUser(EisUser eisUser) {
        this.eisUser = eisUser;
    }
}
