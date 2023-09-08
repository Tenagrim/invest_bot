create table USER_STATE
(
    ID      bigserial primary key not null,
    SYSNAME varchar(20)
);

create table Q_USER
(
    ID          bigserial primary key,
    EXTERNAL_ID varchar(50),
    CHAT_ID     bigint,
    USER_ID     bigint,
    NICKNAME    varchar(50),
    STATE_ID    bigint references USER_STATE (ID) default 0,
    CREATE_DATE timestamp                          default now()
);

create table NODE_POSITION(
      ID bigserial primary key,
      X real,
      Y real
);

create table CHAPTER_TYPE(
     ID   bigserial primary key,
     SYSNAME varchar(100),
     NAME varchar(100)
);

create sequence chapter_id_seq start 1000;
create table CHAPTER
(
    ID   bigint NOT NULL DEFAULT nextval('chapter_id_seq') primary key ,
    TEXT text,
    NOTE varchar(100),
    NODE_POSITION_ID bigint references NODE_POSITION(ID),
    CHAPTER_TYPE_ID bigint references CHAPTER_TYPE(ID)
);
ALTER SEQUENCE chapter_id_seq OWNED BY CHAPTER.ID;

create table CHAPTER_BUTTON
(
    ID   bigserial primary key,
    TEXT varchar(50),
    CHAPTER_ID bigint not null references CHAPTER (ID),
    TARGET_CHAPTER_ID bigint references CHAPTER (ID),
    PLACEMENT integer default 0
);

create table CHAPTER_ATTACHEMENT
(
    ID bigserial primary key,
    CHAPTER_ID bigint references CHAPTER (ID)
);

create table COMMAND(
                        ID bigserial primary key,
                        TEXT varchar(50) unique,
                        CHAPTER_ID bigint references CHAPTER (ID)
);