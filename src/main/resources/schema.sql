create TABLE `page` (
  `page_id` bigint generated by default as identity,
  `title` varchar(30) NOT NULL,
  `content` text,
  `parent_id` bigint DEFAULT NULL,
  primary key(`page_id`)
);
create index parent_id on page(parent_id);