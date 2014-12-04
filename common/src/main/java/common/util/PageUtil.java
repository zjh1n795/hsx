package common.util;
/**
 * 分页类
 * 初始化时，一定要先设置pageSize 再设置 currentPage,最后设置totalRow。
 * @author jie
 *
 */
public class PageUtil {

    private int curPage = 1; // 当前页
    private int pageSize = 10; // 每页多少行
    private long totalRow; // 共多少行
    private long start;// 当前页起始行
    private long end;// 结束行
    private int totalPage; // 共多少页

    public PageUtil(){
    }
    
    public PageUtil(int curPage,long totalRow){
    	setCurPage(curPage);
    	setTotalRow(totalRow);
    }
    
    public PageUtil(int curPage,int pageSize,long totalRow){
    	setCurPage(curPage);
    	setPageSize(pageSize);
    	setTotalRow(totalRow);
    }
    
    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        if (curPage < 1) {
            curPage = 1;
        } else {
            start = pageSize * (curPage - 1);
        }
        end = start + pageSize > totalRow ? totalRow : start + pageSize;
        this.curPage = curPage;
    }

    public long getStart() {
        // start=curPage*pageSize;
        return start;
    }

    public long getEnd() {

        return end;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(long totalRow) {
        totalPage =(int)(totalRow%pageSize==0?totalRow/pageSize:((totalRow/pageSize)+1));
    }

    public int getTotalPage() {

        return this.totalPage;
    }

}