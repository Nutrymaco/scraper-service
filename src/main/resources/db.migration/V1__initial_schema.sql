create table parser_service.vacancy
(
    id  serial not null,
    name            varchar,
    description     varchar,
    company_name    varchar,
    city            varchar,
    created         date,
    salary_from     integer,
    salary_to       integer,
    currency        character(3),
    experience_from integer,
    experience_to   integer,
    url varchar
) partition by range (created);

create table parser_service.vacancy_2020_10 partition of parser_service.vacancy
    for values from ('2020-10-01') to ('2020-11-01');

create table parser_service.vacancy_2020_11 partition of parser_service.vacancy
    for values from ('2020-11-01') to ('2020-12-01');

create table parser_service.vacancy_2020_12 partition of parser_service.vacancy
    for values from ('2020-12-01') to ('2021-01-01');

alter table parser_service.vacancy
    owner to nutrymaco;