/* 생성된 테이블에 속성 추가하기 */
alter table membership add column master_yn ENUM('Y', 'N') NOT NULL DEFAULT 'N';

desc membership;

DROP TABLE IF EXISTS membership;       /* 멤버쉽 DB테이블 다시 만듬 */

SELECT * FROM membership;

/* 생성된 테이블 속성 수정하기 */
alter table membership modify column user_birth VARCHAR(10) NOT NULL;