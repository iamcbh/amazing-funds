
package money.funds.model;

import org.springframework.stereotype.Component;

/**
 * <p>
 * <b> Money Fund details. </b>
 * </p>
 */
@Component
public class MoneyFundDetails {

    private String fundCode;
    private String fundName;
    private double returnYTD;
    private double return1Week;
    private double return1Month;
    private double return3Month;
    private double return6Month;
    private double return1Year;
    private double return2Year;
    private double return3Year;
    private double return5Year;
    private double return10Year;
    private double returnInception;

    /**
     * @return the fundCode
     */
    public String getFundCode() {
        return this.fundCode;
    }

    /**
     * @param fundCode
     *            the fundCode to set
     */
    public void setFundCode(final String fundCode) {
        this.fundCode = fundCode;
    }

    /**
     * @return the fundName
     */
    public String getFundName() {
        return this.fundName;
    }

    /**
     * @param fundName
     *            the fundName to set
     */
    public void setFundName(final String fundName) {
        this.fundName = fundName;
    }

    /**
     * @return the returnYTD
     */
    public double getReturnYTD() {
        return this.returnYTD;
    }

    /**
     * @param returnYTD
     *            the returnYTD to set
     */
    public void setReturnYTD(final double returnYTD) {
        this.returnYTD = returnYTD;
    }

    /**
     * @return the return1Week
     */
    public double getReturn1Week() {
        return this.return1Week;
    }

    /**
     * @param return1Week
     *            the return1Week to set
     */
    public void setReturn1Week(final double return1Week) {
        this.return1Week = return1Week;
    }

    /**
     * @return the return1Month
     */
    public double getReturn1Month() {
        return this.return1Month;
    }

    /**
     * @param return1Month
     *            the return1Month to set
     */
    public void setReturn1Month(final double return1Month) {
        this.return1Month = return1Month;
    }

    /**
     * @return the return3Month
     */
    public double getReturn3Month() {
        return this.return3Month;
    }

    /**
     * @param return3Month
     *            the return3Month to set
     */
    public void setReturn3Month(final double return3Month) {
        this.return3Month = return3Month;
    }

    /**
     * @return the return6Month
     */
    public double getReturn6Month() {
        return this.return6Month;
    }

    /**
     * @param return6Month
     *            the return6Month to set
     */
    public void setReturn6Month(final double return6Month) {
        this.return6Month = return6Month;
    }

    /**
     * @return the return1Year
     */
    public double getReturn1Year() {
        return this.return1Year;
    }

    /**
     * @param return1Year
     *            the return1Year to set
     */
    public void setReturn1Year(final double return1Year) {
        this.return1Year = return1Year;
    }

    /**
     * @return the return2Year
     */
    public double getReturn2Year() {
        return this.return2Year;
    }

    /**
     * @param return2Year
     *            the return2Year to set
     */
    public void setReturn2Year(final double return2Year) {
        this.return2Year = return2Year;
    }

    /**
     * @return the return3Year
     */
    public double getReturn3Year() {
        return this.return3Year;
    }

    /**
     * @param return3Year
     *            the return3Year to set
     */
    public void setReturn3Year(final double return3Year) {
        this.return3Year = return3Year;
    }

    /**
     * @return the return5Year
     */
    public double getReturn5Year() {
        return this.return5Year;
    }

    /**
     * @param return5Year
     *            the return5Year to set
     */
    public void setReturn5Year(final double return5Year) {
        this.return5Year = return5Year;
    }

    /**
     * @return the return10Year
     */
    public double getReturn10Year() {
        return this.return10Year;
    }

    /**
     * @param return10Year
     *            the return10Year to set
     */
    public void setReturn10Year(final double return10Year) {
        this.return10Year = return10Year;
    }

    /**
     * @return the returnInception
     */
    public double getReturnInception() {
        return this.returnInception;
    }

    /**
     * @param returnInception
     *            the returnInception to set
     */
    public void setReturnInception(final double returnInception) {
        this.returnInception = returnInception;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "FundDetails [fundCode=" + this.fundCode + ", fundName=" + this.fundName + ", returnYTD=" + this.returnYTD
            + ", return1Week=" + this.return1Week + ", return1Month=" + this.return1Month + ", return3Month=" + this.return3Month
            + ", return6Month=" + this.return6Month + ", return1Year=" + this.return1Year + ", return2Year=" + this.return2Year
            + ", return3Year=" + this.return3Year + ", return5Year=" + this.return5Year + ", return10Year=" + this.return10Year
            + ", returnInception=" + this.returnInception + "]";
    }

}
