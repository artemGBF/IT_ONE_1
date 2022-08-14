create table tables(
    table_name text primary key,
    columns_amount int,
    primary_key text
);

create table columns(
    id int primary key,
    table_id text references tables(table_name) on delete cascade,
    title text,
    type text
);

create table table_queries(
    query_id int primary key,
    table_name varchar(50),
    query varchar(120)
);

create table single_queries(
    query_id int primary key,
    query varchar(120)
);

create table reports(
    report_id int primary key,
    table_amount int
);

create table report_table(
    id serial primary key,
    report_id int references reports(report_id) on delete cascade,
    table_id int references tables(table_name) on delete cascade
);