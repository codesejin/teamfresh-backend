-- sql
INSERT INTO carrier (carrier_name)
VALUES ('CJ_LOGISTICS'),
       ('LOTTE_LOGISTICS'),
       ('HYUNDAI_LOGISTICS'),
       ('DHL'),
       ('KOREA_POST');
INSERT INTO customer (contact_number, contact_person, name)
VALUES ('John Doe','123-456-7890','ABC Company'),
       ('John','123-456-7890','choco Company'),
       ('Doe','123-456-7890','teamteam'),
       ('park','123-456-7890','rocket fresh'),
       ('seijin','123-456-7890','coupang');
INSERT INTO voc
(blame_type, voc_content, created_at, updated_at, claim_entry_type, claim_status, customer_id, carrier_id)
VALUES ('CARRIER', 'WRONG_DELIVERY_LOCATION', CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6),'KAKAOTALK','INCOMING',1,1),
       ('CUSTOMER', 'DAMAGED_ITEM', CURRENT_TIMESTAMP(6),NOW(6),'NAVER_TALK','INCOMING',1,2),
       ('CARRIER', 'DELAYED_DELIVERY', CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6),'PHONE','INCOMING',2,3),
       ('CUSTOMER', 'MISSING_ITEM', CURRENT_TIMESTAMP(6),NOW(6),'KAKAOTALK','INCOMING',2,4),
       ('CARRIER', 'DELAYED_DELIVERY', CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6),'PHONE','INCOMING',3,5);


