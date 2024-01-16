CREATE TABLE insurance
(
  idx            INT NOT NULL AUTO_INCREMENT,           /* 정수형 데이터 타입으로, NULL 값은 허용하지 않음(NOT NULL), 자동으로 증가하는 값(AUTO_INCREMENT), 기본키(PRIMARY KEY)로 설정되어 있음 */
  
  ins_co         VARCHAR(10)  NOT NULL,                 /* 보험회사          (Insurance Company) */
  ins_type       VARCHAR(10)  NOT NULL,                 /* 보험종목          (Insurance Type) 최대 10자의 문자열 데이터를 저장하는 열이며, NULL 값은 허용하지 않음(NOT NULL) */
  contract_date  VARCHAR(20)  NOT NULL,                 /* 계약체결일        (Contract_Date) */
  contract_no    VARCHAR(100) NOT NULL,                 /* 계약번호 및 증권번호 (Contract Number) */
  contractor     VARCHAR(100) NOT NULL,                 /* 계약자           (Contractor) */
  insured        VARCHAR(100) NOT NULL,                 /* 피보험자          (Insured) */
  prod_name      VARCHAR(500) NOT NULL,                 /* 상품명           (Product Name) */
  payt_exp       VARCHAR(20)  NOT NULL,                 /* 납기&만기         (Payment & Expiry) */
  premium        INT NOT NULL,                          /* 납입보험료         (Premium) 정수형 데이터 타입으로, NULL 값은 허용하지 않음(NOT NUL) */
  memo_col       VARCHAR(10000) NULL,                   /* 메모란           (Memo Column) */
   
  renew_prod_yn  ENUM('Y', 'N') NOT NULL DEFAULT 'N',   /* 갱신형 상품 여부   (Renewable Product) 열거형(ENUM) 데이터 타입으로, NULL 값은 허용하지 않음(NOT NULL), 기본값으로 N이 설정되어 있음(DEFAULT 'N') */
  digi_sig_yn    ENUM('Y', 'N') NOT NULL DEFAULT 'N',   /* 전자서명 여부      (Digiral Signature) */
  delete_yn      ENUM('Y', 'N') NOT NULL DEFAULT 'N',   
  
  insert_time    DATETIME NOT NULL DEFAULT NOW(),       /* 등록일 (insertTime) 날짜와 시간을 저장하는 데이터 타입(DATETIME)의 열이며, NULL 값은 허용하지 않음(NOT NULL), 현재 날짜와 시간이 기본값으로 설정되어 있음(DEFAULT NOW() ) */
  update_time    DATETIME NULL,                         /* 등록일 날짜와 시간 (updateTime)을 저장하는 데이터 타입(DATETIME)의 열이며, NULL 값을 허용함(NULL) :: 업데이트가 발생하면 해당 시간으로 업데이트 */
  delete_time    DATETIME NULL,                         /* 삭제일 날짜와 시간 (deleteTime)을 저장하는 데이터 타입(DATETIME)의 열이며, NULL 값을 허용함(NULL) :: 삭제가 발생하면 해당 시간으로 설정 */
  
  PRIMARY KEY (idx)                                     /* idx 열을 기본키로 설정, 기본키는 각 행을 고유하게 식별하는 데 사용된다. */
);


CREATE TABLE carinsurance                           /* 자동차 글쓰기 */
(
	idx                INT NOT NULL AUTO_INCREMENT,
    
    renew_date         VARCHAR(100) NOT NULL,       /* 갱신 날짜 */
    car_insco          VARCHAR(10)  NOT NULL,       /* 보험회사 */
    car_number         VARCHAR(20)  NOT NULL,       /* 차량번호 */
    car_contractor     VARCHAR(100) NOT NULL,       /* 계약자 */
    car_insured        VARCHAR(100) NOT NULL,       /* 피보험자 */
    
    car_instype        VARCHAR(20)  NOT NULL,       /* 보험종목 */
    car_name           VARCHAR(100) NOT NULL,       /* 차량명 */
	car_code           VARCHAR(100) NOT NULL,       /* 차명코드 */
    model_year         VARCHAR(10)  NOT NULL,       /* 연식 */
    driving_res        VARCHAR(10)  NOT NULL,       /* 운전자&연령한정 */
    driving_exp        VARCHAR(100) NOT NULL,       /* 가입경력자 */
    driving_low        VARCHAR(100) NOT NULL,       /* 최저연령자 */
    special_agr        VARCHAR(100) NOT NULL,       /* 특약 */
    identical_sec      VARCHAR(100) NOT NULL,       /* 동일증권 */
    car_memo           VARCHAR(10000) NULL,         /* 메모 */
    
    car_digisi_yn      ENUM('Y', 'N') NOT NULL DEFAULT 'N',   /* 전자서명 여부 */
	car_delete_yn      ENUM('Y', 'N') NOT NULL DEFAULT 'N',   
    
	car_insert_time    DATETIME NOT NULL DEFAULT NOW(),       /* 등록일          (insertTime) 날짜와 시간을 저장하는 데이터 타입(DATETIME)의 열이며, NULL 값은 허용하지 않음(NOT NULL), 현재 날짜와 시간이 기본값으로 설정되어 있음(DEFAULT NOW() ) */
	car_update_time    DATETIME NULL,                         /* 등록일 날짜와 시간 (updateTime)을 저장하는 데이터 타입(DATETIME)의 열이며, NULL 값을 허용함(NULL) :: 업데이트가 발생하면 해당 시간으로 업데이트 */
    car_delete_time    DATETIME NULL,                         /* 삭제일 날짜와 시간 (deleteTime)을 저장하는 데이터 타입(DATETIME)의 열이며, NULL 값을 허용함(NULL) :: 삭제가 발생하면 해당 시간으로 설정 */
    
    PRIMARY KEY (idx)
);


CREATE TABLE membership
(
	user_idx          INT NOT NULL AUTO_INCREMENT,
    
    user_id           VARCHAR(100) NOT NULL,                  /* 사용자 아이디 */
    user_pw           VARCHAR(100) NOT NULL,                  /* 사용자 패스워드 */
    user_email        VARCHAR(100) NOT NULL,                  /* 사용자 이메일 */
    user_name         VARCHAR(100) NOT NULL,                  /* 사용자 이름 */
    user_birth        VARCHAR(10) NOT NULL,                  /* 사용자 생년월일 */
    user_gender       ENUM('M', 'F') NOT NULL DEFAULT 'F',    /* 사용자 성별 */
    user_phone        VARCHAR(100) NOT NULL,                  /* 사용자 연락처 */
    quit_yn           ENUM('Y', 'N') NOT NULL DEFAULT 'N',    /* 탈퇴여부 */
    master_yn         ENUM('Y', 'N') NOT NULL DEFAULT 'N',    /* 슈퍼계정 & 일반계정 */
    
    PRIMARY KEY (user_idx)
);



