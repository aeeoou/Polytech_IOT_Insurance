package com.insurance.paging;

import jakarta.servlet.http.HttpSession;

public class PaginationInfo
{
	// 페이지 번호 계산에 필요한 Criteria 클래스의 멤버 변수들에 대한 정보를 가지는 변수
	private Criteria criteria;
	// 전체 데이터의 개수
	private int totalRecordCount;
	// 전체 페이지의 개수
	private int totalPageCount;
	// pageSize = 10, currentPageNo = 3 이라면, 1페이지를 의미
	private int firstPage;
	// pageSize = 10, currentPageNo = 3 이라면, 10페이지를 의미
	private int lastPage;
	// Criteria 클래스의 getStartPage 메서드를 대체해서 LIMIT 구문의 첫 번째 값에 사용되는 변수 ???
	private int firstRecordIndex;
	// 오라클과 같이 LIMIT 구문이 존재하지 않고, 인라인 뷰(FROM 절 서브 쿼리)를 사용해야 하는 데이터베이스에서의 활용을 위한 변수 (MySQL을 기반으로 진행하기 때문에 사용하지 않음)
	private int lastRecordIndex;
	// 이전 페이지가 존재하는 지를 판단하기 위한 변수
	private boolean hasPreviousPage;
	// 다음 페이지가 존재하는 지를 구분하는 용도로 사용된다.
	private boolean hasNextPage;
	
	// PaginationInfo 메서드 :: 잘못된 값이 들어왔을 때, 기본값으로 지정 (페이징 번호 계산)
	public PaginationInfo(Criteria criteria)
	{
		if (criteria.getCurrentPageNo() < 1)
		{
			criteria.setCurrentPageNo(1);
		}
		if (criteria.getRecordsPerPage() < 1 || criteria.getRecordsPerPage() > 100)
		{
			criteria.setRecordsPerPage(10);
		}
		if (criteria.getPageSize() < 5 || criteria.getPageSize() > 20)
		{
			criteria.setPageSize(10);
		}
		this.criteria = criteria;
	}
	
	// 파라미터로 넘어온 전체 데이터 개수를 PaginationInfo 클래스의 전체 데이터 개수에 저장 (전체 데이터 개수가 1개 이상이면 페이지 번호를 계산하는 calculation 메서드 실행)
	public void setTotalRecordCount(int totalRecordCount)
	{
		this.totalRecordCount = totalRecordCount;
		
		if (totalRecordCount > 0)
		{
			calculation();
		}
	}
	
	private void calculation()
	{
		/* 전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를저장) */
		// ( (전체 데이터 개수 -1) / 페이지당 출력할 데이터 개수 ) +1을 통해 전체 페이지 개수 산출
		totalPageCount = ((totalRecordCount -1) / criteria.getRecordsPerPage()) +1;
		if (criteria.getCurrentPageNo() > totalPageCount)
		{
			criteria.setCurrentPageNo(totalPageCount);
		}
		
		/* 페이지 리스트의 첫페이지 번호 */
		// ( (현재 페이지 번호 -1) / 화면 하단의 페이지 개수 ) * 화면 하단의 페이지 개수 +1을 통해 산출
		firstPage = ((criteria.getCurrentPageNo() -1) / criteria.getPageSize()) * criteria.getPageSize() +1;
		
		/* 페이지 리스트의 마지막 페이지 번호 (마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장) */
		// (첫 페이지 번호 + 화면 하단의 페이지 개수) -1을 통해 마지막 페이지 번호 산출
		lastPage = firstPage + criteria.getPageSize() -1;
		if (lastPage > totalPageCount )
		{
			lastPage = totalPageCount;
		}
		
		/* SQL의 조건절에 사용되는 첫 RNUM */
		// Criteria 클래스의 getStartPage 메서드를 대신해서 LIMIT 구문의 첫 번째 값에 들어갈 데이터
		firstRecordIndex = (criteria.getCurrentPageNo() -1) * criteria.getRecordsPerPage();
		
		/* SQL의 조건절에 사용되는 마지막 RNUM */
		// 오라클 DB에서 사용할 경우 대비 (우리는 사용하지 않음)
		lastRecordIndex = criteria.getCurrentPageNo() * criteria.getRecordsPerPage();
		
		/* 이전 페이지 존재 여부 */
		// 이전 페이지 존재 여부를 확인
		hasPreviousPage = firstPage != 1;
		
		/* 다음 페이지 존재 여부 */
		// (마지막 페이지 번호 * 페이지당 출력할 데이터의 개수)가 전체 데이터 개수보다 크거나 같으면 false, 작으면 true
		hasNextPage = (lastPage * criteria.getRecordsPerPage()) < totalRecordCount;
	}

	/* Getter Setter 생성자 생성 */
	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getLastRecordIndex() {
		return lastRecordIndex;
	}

	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	@Override
	public String toString() {
		return "PaginationInfo [criteria=" + criteria + ", totalRecordCount=" + totalRecordCount + ", totalPageCount="
				+ totalPageCount + ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", firstRecordIndex="
				+ firstRecordIndex + ", lastRecordIndex=" + lastRecordIndex + ", hasPreviousPage=" + hasPreviousPage
				+ ", hasNextPage=" + hasNextPage + "]";
	}
}


