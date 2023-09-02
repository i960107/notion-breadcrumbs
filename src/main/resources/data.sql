-- 1 -> 2-> 3-> 4
-- 1 -> 2-> 3-> 7
-- 1 -> 2-> 5-> 6
insert into `page` (`page_id`, `title`, `content`, `parent_id`) VALUES
(1, '[프리온보딩 인턴십] 백엔드 6차', '[프리온보딩 인턴십] 백엔드 6차', NULL);

insert into `page` (`page_id`, `title`, `content`, `parent_id`) VALUES
(2, '인턴십 커리큘럼(4주)', '*** *원티드는 지식 재산권과 개인 정보 등의 권리를 존중합니다. 학습 자료는 학습 목적으로 참가자만 이용할 수 있으며, 지식 재산권을 위배하거나 개인 정보를 침해하는 어떠한 행위도 허용되지 않습니다.*\n\n*** *학습 자료 무단 복제 및 전송(파일 업로드), 배포(출력물의 경우), 도용 등은 저작권법에 위반되는 행위이니 수강생은 학습자료 관리에 유의하시기 바랍니다.*', 1);

insert into `page` (`page_id`, `title`, `content`, `parent_id`) VALUES
(3, 'Week 1-1', NULL, 2);

insert into `page` (`page_id`, `title`, `content`, `parent_id`) VALUES
(4, '1 대 1,000,000을 감당하는 역량의 시작', '강의를 시작하며/ 사용자 수 증가에 따른 시스템 설계', 3);

insert into `page` (`page_id`, `title`, `content`, `parent_id`) VALUES
(5, 'Week 1-2', NULL, 2);

insert into `page` (`page_id`, `title`, `content`, `parent_id`) VALUES
(6, '알고리즘', '알고리즘이란? 알고리즘을 제대로 학습하려면? 컴퓨터가 명려어를 어떻게 처리할까?', 5);

insert into `page` (`page_id`, `title`, `content`, `parent_id`) VALUES
(7, 'Orientation', '멘토소개/ 원티드 프리온보딩 백엔드 인턴십 6차 소개/ 커리큘럼 안내', 3);
