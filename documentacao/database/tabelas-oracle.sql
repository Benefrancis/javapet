-- alter session set "_ORACLE_SCRIPT"=true;

-- CREATE  USER JAVAPET IDENTIFIED BY root DEFAULT tablespace users;

-- grant all privileges to JAVAPET;


create sequence SQ_PESSOAS;
/
create sequence SQ_ANIMAIS;
/
create sequence SQ_SERVICOS;
/


CREATE TABLE "TB_PF"
(
    "ID_PESSOA"     NUMBER(19, 0),
    "NM_PESSOA"     VARCHAR2(255 BYTE),
    "DT_NASCIMENTO" DATE,
    "TP_PESSOA"     VARCHAR2(50 BYTE),
    "NR_CPF"        VARCHAR2(50 BYTE),
    CONSTRAINT TB_PF_PK PRIMARY KEY
        (
         ID_PESSOA
            )
        USING INDEX
            (
            CREATE UNIQUE INDEX IDX_TB_PF_PK ON TB_PF (ID_PESSOA ASC)
            )
);
/

CREATE TABLE "TB_PJ"
(
    "ID_PESSOA"     NUMBER(19, 0),
    "NM_PESSOA"     VARCHAR2(255 BYTE),
    "DT_NASCIMENTO" DATE,
    "TP_PESSOA"     VARCHAR2(50 BYTE),
    "NR_CNPJ"       VARCHAR2(50 BYTE),
    CONSTRAINT TB_PJ_PK PRIMARY KEY
        (
         ID_PESSOA
            )
        USING INDEX
            (
            CREATE UNIQUE INDEX IDX_TB_PJ_PK ON TB_PJ (ID_PESSOA ASC)
            )
);
/


CREATE TABLE TB_ANIMAL
(
    ID_ANIMAL NUMBER(19, 0) NOT NULL,
    NM_ANIMAL VARCHAR2(255 BYTE),
    RACA      VARCHAR2(255 BYTE),
    DS_ANIMAL VARCHAR2(4000 BYTE),
    TP_ANIMAL VARCHAR2(255 BYTE),
    DONO      NUMBER(19, 0),
    CONSTRAINT TB_ANIMAL_PK PRIMARY KEY
        (
         ID_ANIMAL
            )
        USING INDEX
            (
            CREATE UNIQUE INDEX IDX_TB_ANIMAL_PK ON TB_ANIMAL (ID_ANIMAL ASC)
            )
);
/


CREATE TABLE TB_SERVICO
(
    ID_SERVICO    NUMBER(19),
    TP_SERVICO    VARCHAR2(50),
    DS_SERVICO    VARCHAR2(4000),
    DT_REALIZACAO DATE,
    ANIMAL        NUMBER(19),
    CONSTRAINT TB_SERVICO_PK PRIMARY KEY
        (
         ID_SERVICO
            )
        USING INDEX
            (
            CREATE UNIQUE INDEX IDX_TB_SERVICO_PK ON TB_SERVICO (ID_SERVICO ASC)
            )
);
/


create or replace trigger TG_SQ_SERVICOS
    before insert or update
    on TB_SERVICO
    for each row
begin
    if inserting and :new.ID_SERVICO is null or :new.ID_SERVICO < 1 then
        :new.ID_SERVICO := SQ_SERVICOS.nextval;
    end if;
end;
/

create or replace trigger TG_SQ_ANIMAIS
    before insert or update
    on TB_ANIMAL
    for each row
begin
    if inserting and :new.ID_ANIMAL is null or :new.ID_ANIMAL < 1 then
        :new.ID_ANIMAL := SQ_ANIMAIS.nextval;
    end if;
end;
/

create or replace trigger TG_SQ_PF
    before insert or update
    on TB_PF
    for each row
begin
    if inserting and :new.ID_PESSOA is null or :new.ID_PESSOA < 1 then
        :new.ID_PESSOA := SQ_PESSOAS.nextval;
    end if;
end;
/
create or replace trigger TG_SQ_PJ
    before insert or update
    on TB_PJ
    for each row
begin
    if inserting and :new.ID_PESSOA is null or :new.ID_PESSOA < 1 then
        :new.ID_PESSOA := SQ_PESSOAS.nextval;
    end if;
end;
/

CREATE INDEX TB_PF_NM_PESOA ON TB_PF (NM_PESSOA ASC);
/
CREATE INDEX TB_PJ_NM_PESSOA ON TB_PJ (NM_PESSOA ASC);
/
ALTER TABLE TB_PJ
    MODIFY (ID_PESSOA NOT NULL);
/
ALTER TABLE TB_PF
    MODIFY (ID_PESSOA NOT NULL);
/



ALTER TABLE TB_SERVICO
    ADD CONSTRAINT TB_SERVICO_FK_ANIMAL
        FOREIGN KEY (ID_SERVICO)
            REFERENCES TB_ANIMAL (ID_ANIMAL)
                ENABLE;
/