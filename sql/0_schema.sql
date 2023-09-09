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

create sequence node_position_id_seq start 1000;
create table NODE_POSITION(
      ID   bigint NOT NULL DEFAULT nextval('node_position_id_seq') primary key ,
      X real,
      Y real
);
ALTER SEQUENCE node_position_id_seq OWNED BY NODE_POSITION.ID;

create table CHAPTER_TYPE(
     ID   bigserial primary key,
     SYSNAME varchar(100),
     NAME varchar(100)
);

create table BOT_CONFIG_VERSION (
         ID   bigserial primary key
);

create table DATA_VERSION(
     ID   bigserial primary key,
     CREATE_DATE timestamp not null default now(),
     UPDATE_DATE timestamp not null default now(),
     BOT_CONFIG_VERSION_ID bigint references BOT_CONFIG_VERSION(ID),
     NOTE varchar(255)
);

create table BOT_CONFIG(
       ID   bigserial primary key,
       SYSNAME varchar(50) unique ,
       NAME varchar(100),
       CURRENT_VERSION_ID bigint references DATA_VERSION(ID),
       BOT_CONFIG_VERSION_ID bigint references BOT_CONFIG_VERSION(ID)
);


create sequence chapter_id_seq start 1000;
create sequence chapter_item_id_seq start 1000;
create table CHAPTER
(
    ID   bigint NOT NULL DEFAULT nextval('chapter_id_seq') primary key ,
    ITEM_ID bigint not null default nextval('chapter_item_id_seq'),
    TEXT text,
    NOTE varchar(100),
    NODE_POSITION_ID bigint references NODE_POSITION(ID),
    CHAPTER_TYPE_ID bigint references CHAPTER_TYPE(ID),
    DATA_VERSION_ID bigint references DATA_VERSION(ID)
);
ALTER SEQUENCE chapter_id_seq OWNED BY CHAPTER.ID;
ALTER SEQUENCE chapter_item_id_seq OWNED BY CHAPTER.ITEM_ID;

create table CHAPTER_BUTTON
(
    ID   bigserial primary key,
    TEXT varchar(50),
    CHAPTER_ID bigint references CHAPTER (ID),
    TARGET_CHAPTER_ID bigint references CHAPTER (ID),
    PLACEMENT integer default 0
);

create table CHAPTER_ATTACHEMENT
(
    ID bigserial primary key,
    CHAPTER_ID bigint references CHAPTER (ID),
    FILENAME varchar(100)
);

create table COMMAND(
                        ID bigserial primary key,
                        TEXT varchar(50) unique,
                        CHAPTER_ID bigint references CHAPTER (ID)
);