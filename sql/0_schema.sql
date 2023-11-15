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

create table APP_USER(
     ID   bigserial primary key,
     USERNAME varchar(255) unique,
     PASSWORD varchar(255),
     LOCKED boolean default false,
     ENABLED boolean default false
);

create table APP_AUTHORITY(
    ID   bigserial primary key,
    AUTHORITY varchar(100)
);

create table APP_USER_AUTHORITY(
     ID   bigserial primary key,
     USER_ID bigint references APP_USER(ID),
     AUTHORITY_ID bigint references APP_AUTHORITY(ID)
);

create table TG_USER(
        ID   bigserial primary key,
        EXTERNAL_ID bigint not null unique,
        FIRST_NAME varchar(255),
        USER_NAME varchar(255),
        LANGUAGE_CODE varchar(5),
        IS_BOT boolean
);

create table CONTACT_TYPE(
     ID   bigserial primary key,
     SYSNAME varchar(100),
     NAME varchar(100)
);

create table TG_CONTACT(
       ID   bigserial primary key,
       CONTACT_TYPE_ID bigint references CONTACT_TYPE(ID),
       USER_ID bigint references TG_USER(ID),
       VALUE varchar(255),
       CREATE_DATE timestamp not null default now()
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

create table BOT_CONFIG_PROPERTIES(
    ID bigserial primary key,
    SYSNAME varchar(100),
    DESCRIPTION varchar(255)
);

create table BOT_CONFIG_PROPERTIES_VALUES(
     ID bigserial primary key,
     BOT_CONFIG_ID bigint references BOT_CONFIG(ID),
     PROPERTY_ID bigint references BOT_CONFIG_PROPERTIES(ID),
     VALUE varchar(100)
);

create table CHAPTER_MARK(
     ID   bigserial primary key,
     KEY bigint not null,
     BOT_CONFIG_ID bigint references BOT_CONFIG(ID),
     NAME varchar(50)
);


create sequence chapter_id_seq start 1000;
create sequence chapter_item_id_seq start 1000;
create table CHAPTER
(
    ID   bigint NOT NULL DEFAULT nextval('chapter_id_seq') primary key ,
    ITEM_ID bigint not null default nextval('chapter_item_id_seq'),
    TEXT text,
    NOTE varchar(100),
    MARKS_KEY bigint not null default 0,
    NODE_POSITION_ID bigint references NODE_POSITION(ID),
    CHAPTER_TYPE_ID bigint references CHAPTER_TYPE(ID),
    DATA_VERSION_ID bigint references DATA_VERSION(ID),
    UPDATE_USER_ID bigint references APP_USER(ID),
    UPDATE_DATE timestamp not null default now()
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

create table PARAGRAPH_TYPE(
   ID   bigserial primary key,
   SYSNAME varchar(50),
   DESCRIPTION varchar(100)
);

create table KEYBOARD_TYPE(
  ID   bigserial primary key,
  SYSNAME varchar(50),
  DESCRIPTION varchar(100)
);

create sequence paragraph_id_seq start 1000;
create table PARAGRAPH(
      ID   bigint NOT NULL DEFAULT nextval('paragraph_id_seq') primary key,
      TEXT text,
      PLACEMENT integer not null default 0,
      CHAPTER_ID bigint references CHAPTER (ID),
      PARAGRAPH_TYPE_ID bigint references PARAGRAPH_TYPE (ID) not null default 1,
      KEYBOARD_TYPE_ID bigint references PARAGRAPH_TYPE (ID) not null default 1
);
ALTER SEQUENCE paragraph_id_seq OWNED BY PARAGRAPH.ID;

alter table PARAGRAPH add column PARAGRAPH_TYPE_ID bigint references PARAGRAPH_TYPE (ID);
-- alter table PARAGRAPH add column KEYBOARD_TYPE_ID bigint references PARAGRAPH_TYPE (ID);

create table PARAGRAPH_BUTTON(
     ID   bigserial primary key,
     TEXT varchar(50),
     PARAGRAPH_ID bigint references PARAGRAPH(ID),
     TARGET_CHAPTER_ID bigint references CHAPTER (ID),
     PLACEMENT integer default 0
);

create table ATTACHMENT_TYPE(
   ID bigserial primary key,
   SYSNAME varchar(50),
   DESCRIPTION varchar(100)
);

create table ATTACHMENT
(
    ID bigserial primary key,
    CHAPTER_ID bigint references CHAPTER (ID),
    ATTACHMENT_TYPE_ID bigint references ATTACHMENT_TYPE(ID),
    FILENAME varchar(250)
);

create table COMMAND(
    ID bigserial primary key,
    TEXT varchar(50) unique,
    CHAPTER_ID bigint references CHAPTER (ID)
);

create table INTEGRATION_TYPE(
     ID bigserial primary key,
     SYSNAME varchar(50),
     DESCRIPTION varchar(100)
);

create table INTEGRATION_CREDENTIAL_TYPE(
     ID bigserial primary key,
     SYSNAME varchar(50),
     DESCRIPTION varchar(100),
     UNIQUE (SYSNAME)
);

create table INTEGRATION(
    ID bigserial primary key,
    BOT_CONFIG_ID bigint references BOT_CONFIG(ID),
    INTEGRATION_TYPE_ID bigint references INTEGRATION_TYPE(ID),
    UNIQUE (BOT_CONFIG_ID, INTEGRATION_TYPE_ID)
);

create table INTEGRATION_CREDENTIAL(
   ID bigserial primary key,
   INTEGRATION_CREDENTIAL_TYPE_ID bigint references INTEGRATION_CREDENTIAL_TYPE(ID),
   INTEGRATION_ID bigint references INTEGRATION(ID),
   VALUE text
);

create table INTEGRATION_TRIGGER(
    ID bigserial primary key,
    CHAPTER_ID bigint references CHAPTER(ID),
    INTEGRATION_TYPE_ID bigint references INTEGRATION_TYPE(ID),
    UNIQUE (CHAPTER_ID, INTEGRATION_TYPE_ID)
);

create table INTEGRATION_QUEUE(
    ID bigserial primary key,
    CREATE_DATE timestamp not null default now(),
    ACTUAL boolean not null default true,
    USER_ID bigint not null,
    INTEGRATION_TYPE_ID bigint not null references INTEGRATION_TYPE(ID),
    CHAPTER_ID bigint not null
);