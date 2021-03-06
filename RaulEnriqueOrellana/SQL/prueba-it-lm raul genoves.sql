PGDMP         +                w            prueba_it_lm    10.3    10.3 +    +           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            ,           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            -           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                        2615    68366    pit_lifebank    SCHEMA        CREATE SCHEMA pit_lifebank;
    DROP SCHEMA pit_lifebank;
             postgres    false                        2615    68482    pit_lifebank_account    SCHEMA     $   CREATE SCHEMA pit_lifebank_account;
 "   DROP SCHEMA pit_lifebank_account;
             postgres    false            �            1259    68468    usr_user_session    TABLE     �   CREATE TABLE pit_lifebank.usr_user_session (
    uus_session_id integer NOT NULL,
    uus_token character varying,
    usr_user_name_id character varying,
    uus_session_date timestamp without time zone,
    uus_session_status integer
);
 *   DROP TABLE pit_lifebank.usr_user_session;
       pit_lifebank         postgres    false    5            �            1259    68466 #   usr_user_session_uus_session_id_seq    SEQUENCE     �   CREATE SEQUENCE pit_lifebank.usr_user_session_uus_session_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 @   DROP SEQUENCE pit_lifebank.usr_user_session_uus_session_id_seq;
       pit_lifebank       postgres    false    200    5            .           0    0 #   usr_user_session_uus_session_id_seq    SEQUENCE OWNED BY     w   ALTER SEQUENCE pit_lifebank.usr_user_session_uus_session_id_seq OWNED BY pit_lifebank.usr_user_session.uus_session_id;
            pit_lifebank       postgres    false    199            �            1259    68367 	   usr_users    TABLE     �   CREATE TABLE pit_lifebank.usr_users (
    usr_user_name character varying(30) NOT NULL,
    usr_second_name character varying(30),
    usr_password character varying,
    usr_first_name character varying,
    usr_status integer
);
 #   DROP TABLE pit_lifebank.usr_users;
       pit_lifebank         postgres    false    5            �            1259    68550    account_trasactions    TABLE     A  CREATE TABLE pit_lifebank_account.account_trasactions (
    t_trasaction_id character varying NOT NULL,
    t_transaction_detail character varying,
    t_trasaction_account_id character varying,
    t_transaction_amount numeric,
    t_transaction_date timestamp without time zone,
    t_autorization character varying
);
 5   DROP TABLE pit_lifebank_account.account_trasactions;
       pit_lifebank_account         postgres    false    6            �            1259    68529    account_type_details    TABLE     �   CREATE TABLE pit_lifebank_account.account_type_details (
    cc_account_type_detail_id integer NOT NULL,
    cc_account_type_detail_name character varying(15),
    cc_account_type_detail_type_id integer
);
 6   DROP TABLE pit_lifebank_account.account_type_details;
       pit_lifebank_account         postgres    false    6            �            1259    68527 4   c_account_type_details_cc_account_type_detail_id_seq    SEQUENCE     �   CREATE SEQUENCE pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 Y   DROP SEQUENCE pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq;
       pit_lifebank_account       postgres    false    6    206            /           0    0 4   c_account_type_details_cc_account_type_detail_id_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq OWNED BY pit_lifebank_account.account_type_details.cc_account_type_detail_id;
            pit_lifebank_account       postgres    false    205            �            1259    68490    type_accounts    TABLE     �   CREATE TABLE pit_lifebank_account.type_accounts (
    cc_type_account_id integer NOT NULL,
    cc_type_account_name character varying(15)
);
 /   DROP TABLE pit_lifebank_account.type_accounts;
       pit_lifebank_account         postgres    false    6            �            1259    68488 &   c_type_accounts_cc_type_account_id_seq    SEQUENCE     �   CREATE SEQUENCE pit_lifebank_account.c_type_accounts_cc_type_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 K   DROP SEQUENCE pit_lifebank_account.c_type_accounts_cc_type_account_id_seq;
       pit_lifebank_account       postgres    false    203    6            0           0    0 &   c_type_accounts_cc_type_account_id_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE pit_lifebank_account.c_type_accounts_cc_type_account_id_seq OWNED BY pit_lifebank_account.type_accounts.cc_type_account_id;
            pit_lifebank_account       postgres    false    202            �            1259    68496    client_accounts    TABLE     �   CREATE TABLE pit_lifebank_account.client_accounts (
    cc_client_account_id character varying(30) NOT NULL,
    cc_client_account_client_id character varying(30) NOT NULL,
    cc_client_type_account_id integer,
    cc_amount numeric
);
 1   DROP TABLE pit_lifebank_account.client_accounts;
       pit_lifebank_account         postgres    false    6            �            1259    68483    clients    TABLE     �   CREATE TABLE pit_lifebank_account.clients (
    cc_client_id character varying(30) NOT NULL,
    cc_client_name character varying(30)
);
 )   DROP TABLE pit_lifebank_account.clients;
       pit_lifebank_account         postgres    false    6            �
           2604    68471    usr_user_session uus_session_id    DEFAULT     �   ALTER TABLE ONLY pit_lifebank.usr_user_session ALTER COLUMN uus_session_id SET DEFAULT nextval('pit_lifebank.usr_user_session_uus_session_id_seq'::regclass);
 T   ALTER TABLE pit_lifebank.usr_user_session ALTER COLUMN uus_session_id DROP DEFAULT;
       pit_lifebank       postgres    false    200    199    200            �
           2604    68532 .   account_type_details cc_account_type_detail_id    DEFAULT     �   ALTER TABLE ONLY pit_lifebank_account.account_type_details ALTER COLUMN cc_account_type_detail_id SET DEFAULT nextval('pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq'::regclass);
 k   ALTER TABLE pit_lifebank_account.account_type_details ALTER COLUMN cc_account_type_detail_id DROP DEFAULT;
       pit_lifebank_account       postgres    false    206    205    206            �
           2604    68493     type_accounts cc_type_account_id    DEFAULT     �   ALTER TABLE ONLY pit_lifebank_account.type_accounts ALTER COLUMN cc_type_account_id SET DEFAULT nextval('pit_lifebank_account.c_type_accounts_cc_type_account_id_seq'::regclass);
 ]   ALTER TABLE pit_lifebank_account.type_accounts ALTER COLUMN cc_type_account_id DROP DEFAULT;
       pit_lifebank_account       postgres    false    203    202    203            !          0    68468    usr_user_session 
   TABLE DATA                     pit_lifebank       postgres    false    200                      0    68367 	   usr_users 
   TABLE DATA                     pit_lifebank       postgres    false    198            (          0    68550    account_trasactions 
   TABLE DATA                     pit_lifebank_account       postgres    false    207            '          0    68529    account_type_details 
   TABLE DATA                     pit_lifebank_account       postgres    false    206            %          0    68496    client_accounts 
   TABLE DATA                     pit_lifebank_account       postgres    false    204            "          0    68483    clients 
   TABLE DATA                     pit_lifebank_account       postgres    false    201            $          0    68490    type_accounts 
   TABLE DATA                     pit_lifebank_account       postgres    false    203            1           0    0 #   usr_user_session_uus_session_id_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('pit_lifebank.usr_user_session_uus_session_id_seq', 56, true);
            pit_lifebank       postgres    false    199            2           0    0 4   c_account_type_details_cc_account_type_detail_id_seq    SEQUENCE SET     p   SELECT pg_catalog.setval('pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq', 6, true);
            pit_lifebank_account       postgres    false    205            3           0    0 &   c_type_accounts_cc_type_account_id_seq    SEQUENCE SET     b   SELECT pg_catalog.setval('pit_lifebank_account.c_type_accounts_cc_type_account_id_seq', 4, true);
            pit_lifebank_account       postgres    false    202            �
           2606    68481 $   usr_user_session usr_user_session_pk 
   CONSTRAINT     t   ALTER TABLE ONLY pit_lifebank.usr_user_session
    ADD CONSTRAINT usr_user_session_pk PRIMARY KEY (uus_session_id);
 T   ALTER TABLE ONLY pit_lifebank.usr_user_session DROP CONSTRAINT usr_user_session_pk;
       pit_lifebank         postgres    false    200            �
           2606    68444    usr_users usr_users_pk 
   CONSTRAINT     e   ALTER TABLE ONLY pit_lifebank.usr_users
    ADD CONSTRAINT usr_users_pk PRIMARY KEY (usr_user_name);
 F   ALTER TABLE ONLY pit_lifebank.usr_users DROP CONSTRAINT usr_users_pk;
       pit_lifebank         postgres    false    198            �
           2606    68557    account_trasactions newtable_pk 
   CONSTRAINT     x   ALTER TABLE ONLY pit_lifebank_account.account_trasactions
    ADD CONSTRAINT newtable_pk PRIMARY KEY (t_trasaction_id);
 W   ALTER TABLE ONLY pit_lifebank_account.account_trasactions DROP CONSTRAINT newtable_pk;
       pit_lifebank_account         postgres    false    207            �
           2606    68500 $   client_accounts pk_client_account_id 
   CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.client_accounts
    ADD CONSTRAINT pk_client_account_id PRIMARY KEY (cc_client_account_id);
 \   ALTER TABLE ONLY pit_lifebank_account.client_accounts DROP CONSTRAINT pk_client_account_id;
       pit_lifebank_account         postgres    false    204            �
           2606    68487    clients pk_client_id 
   CONSTRAINT     j   ALTER TABLE ONLY pit_lifebank_account.clients
    ADD CONSTRAINT pk_client_id PRIMARY KEY (cc_client_id);
 L   ALTER TABLE ONLY pit_lifebank_account.clients DROP CONSTRAINT pk_client_id;
       pit_lifebank_account         postgres    false    201            �
           2606    68495     type_accounts pk_type_account_id 
   CONSTRAINT     |   ALTER TABLE ONLY pit_lifebank_account.type_accounts
    ADD CONSTRAINT pk_type_account_id PRIMARY KEY (cc_type_account_id);
 X   ALTER TABLE ONLY pit_lifebank_account.type_accounts DROP CONSTRAINT pk_type_account_id;
       pit_lifebank_account         postgres    false    203            �
           2606    68534 &   account_type_details pk_type_detail_id 
   CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.account_type_details
    ADD CONSTRAINT pk_type_detail_id PRIMARY KEY (cc_account_type_detail_id);
 ^   ALTER TABLE ONLY pit_lifebank_account.account_type_details DROP CONSTRAINT pk_type_detail_id;
       pit_lifebank_account         postgres    false    206            �
           2606    68475 $   usr_user_session usr_user_session_fk    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank.usr_user_session
    ADD CONSTRAINT usr_user_session_fk FOREIGN KEY (usr_user_name_id) REFERENCES pit_lifebank.usr_users(usr_user_name) ON UPDATE CASCADE ON DELETE CASCADE;
 T   ALTER TABLE ONLY pit_lifebank.usr_user_session DROP CONSTRAINT usr_user_session_fk;
       pit_lifebank       postgres    false    200    2708    198            �
           2606    68540 $   client_accounts c_client_accounts_fk    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.client_accounts
    ADD CONSTRAINT c_client_accounts_fk FOREIGN KEY (cc_client_type_account_id) REFERENCES pit_lifebank_account.account_type_details(cc_account_type_detail_id) ON UPDATE CASCADE ON DELETE CASCADE;
 \   ALTER TABLE ONLY pit_lifebank_account.client_accounts DROP CONSTRAINT c_client_accounts_fk;
       pit_lifebank_account       postgres    false    2718    206    204            �
           2606    68545 "   client_accounts client_accounts_fk    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.client_accounts
    ADD CONSTRAINT client_accounts_fk FOREIGN KEY (cc_client_account_client_id) REFERENCES pit_lifebank_account.clients(cc_client_id) ON UPDATE CASCADE ON DELETE CASCADE;
 Z   ALTER TABLE ONLY pit_lifebank_account.client_accounts DROP CONSTRAINT client_accounts_fk;
       pit_lifebank_account       postgres    false    2712    204    201            �
           2606    68535 &   account_type_details fk_type_detail_id    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.account_type_details
    ADD CONSTRAINT fk_type_detail_id FOREIGN KEY (cc_account_type_detail_type_id) REFERENCES pit_lifebank_account.type_accounts(cc_type_account_id);
 ^   ALTER TABLE ONLY pit_lifebank_account.account_type_details DROP CONSTRAINT fk_type_detail_id;
       pit_lifebank_account       postgres    false    206    2714    203            �
           2606    68559    account_trasactions newtable_fk    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.account_trasactions
    ADD CONSTRAINT newtable_fk FOREIGN KEY (t_trasaction_account_id) REFERENCES pit_lifebank_account.client_accounts(cc_client_account_id) ON UPDATE CASCADE ON DELETE CASCADE;
 W   ALTER TABLE ONLY pit_lifebank_account.account_trasactions DROP CONSTRAINT newtable_fk;
       pit_lifebank_account       postgres    false    204    2716    207            !   �  x����J1�{�"�*��L23�ē�
��m��
EQq���u[��,|lf��Y�7���Y��w����=>��^���O��s�����fnW���\���z�Z���;Eqn�,R������6:��!���d�GK�:/Վ�0h0D�H�s4�]%q��D�)W����-P�F#�E"�6�H]��0�ɗH��1`C�,C)D�|W��8�"[p�
)n�8R�wl���J���N>�Gd���T'�@�dA��޸�>��@��{
�3:,��)���X_�לO���!��o�O>HG�!������I����|����-��d(E[������41<,	̠Z�n����8P��7ɍ��!vX��@rc��8 ���$�Z�q4\�z2�~��         �   x��1k�0Fw�
mn!��tw�ѩC��q�I��|�P����u�ox����������=�����L�����.��j��?����^z�(�~���b�\n�k	E�0�ϕ<GY�I� Y�ͳjL�DA26.�
$�pSYAy+��*�Z*Z%�'��I����-2W*)���ۇ�<;�_߻��N�9�      (   �  x���]kA�{�ޙ@w83;�J AliLoe���4Y���ߣ-ev�%	'� ��y���r������f����ۯ������X��������j�ͻ��M���]��nn���O5���i�yz���!�6�]�K`7��k��H�D��WV �x���n>��8���	/jc]��{�AxQ�e����zY��N舐�{:�[ns��_o�}��*W�nEU:�Fʶ$K�(!�9=�u�S�4��M����I����{�}���	��Сd���`�H�KZ�d��б��Rvx�'�oc�;Q��bbkB!� eC��b�W#�"� d#3G�A�6��	��c0�ad�QF�3<3��/��0|b62�QD�3"X�&#&��zhNE�6�{�"Â�#3ǌ2J��<7����ℍ0��� J�A ����b��s���9d���\�A�!�\gd�	���d����      '   �   x����
�@��O1�
$ЬK�
)!,Һ.�:��+6z�6z =���?ˋ�RB��'hX�����
�v��y_%��TE�l�p��i�(��٠��^���:����u=���ځ'��e��h��xs�L5���c��� �ӵ�o1�����(�P������      %   �   x���v
Q���W(�,���LKMJ�ˎOLN�/�+�K��L�+�q��}B]�4�L,��L��uM�u\ �DG�/��GӚ˓
��o��`a`�g@%���7�Q04��F��(����� tGm�      "   n   x���v
Q���W(�,���LKMJ�ˎOLN�/�+�K��L�+)Vs�	uV�P70�063056Q�QPw�I�KITH-J�R״��$�8c�qA��9
�E�99�y� � <1k      $   u   x���v
Q���W(�,���LKMJ�ˎOLN�/�+�+�,H�q��}B]�4u�s���5��<�3�hDAjQq~^b��I.JM�,qN,J)��$�IN�yɉE��`s�� __�      +    +           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            ,           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            -           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                        2615    68366    pit_lifebank    SCHEMA        CREATE SCHEMA pit_lifebank;
    DROP SCHEMA pit_lifebank;
             postgres    false                        2615    68482    pit_lifebank_account    SCHEMA     $   CREATE SCHEMA pit_lifebank_account;
 "   DROP SCHEMA pit_lifebank_account;
             postgres    false            �            1259    68468    usr_user_session    TABLE     �   CREATE TABLE pit_lifebank.usr_user_session (
    uus_session_id integer NOT NULL,
    uus_token character varying,
    usr_user_name_id character varying,
    uus_session_date timestamp without time zone,
    uus_session_status integer
);
 *   DROP TABLE pit_lifebank.usr_user_session;
       pit_lifebank         postgres    false    5            �            1259    68466 #   usr_user_session_uus_session_id_seq    SEQUENCE     �   CREATE SEQUENCE pit_lifebank.usr_user_session_uus_session_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 @   DROP SEQUENCE pit_lifebank.usr_user_session_uus_session_id_seq;
       pit_lifebank       postgres    false    200    5            .           0    0 #   usr_user_session_uus_session_id_seq    SEQUENCE OWNED BY     w   ALTER SEQUENCE pit_lifebank.usr_user_session_uus_session_id_seq OWNED BY pit_lifebank.usr_user_session.uus_session_id;
            pit_lifebank       postgres    false    199            �            1259    68367 	   usr_users    TABLE     �   CREATE TABLE pit_lifebank.usr_users (
    usr_user_name character varying(30) NOT NULL,
    usr_second_name character varying(30),
    usr_password character varying,
    usr_first_name character varying,
    usr_status integer
);
 #   DROP TABLE pit_lifebank.usr_users;
       pit_lifebank         postgres    false    5            �            1259    68550    account_trasactions    TABLE     A  CREATE TABLE pit_lifebank_account.account_trasactions (
    t_trasaction_id character varying NOT NULL,
    t_transaction_detail character varying,
    t_trasaction_account_id character varying,
    t_transaction_amount numeric,
    t_transaction_date timestamp without time zone,
    t_autorization character varying
);
 5   DROP TABLE pit_lifebank_account.account_trasactions;
       pit_lifebank_account         postgres    false    6            �            1259    68529    account_type_details    TABLE     �   CREATE TABLE pit_lifebank_account.account_type_details (
    cc_account_type_detail_id integer NOT NULL,
    cc_account_type_detail_name character varying(15),
    cc_account_type_detail_type_id integer
);
 6   DROP TABLE pit_lifebank_account.account_type_details;
       pit_lifebank_account         postgres    false    6            �            1259    68527 4   c_account_type_details_cc_account_type_detail_id_seq    SEQUENCE     �   CREATE SEQUENCE pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 Y   DROP SEQUENCE pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq;
       pit_lifebank_account       postgres    false    6    206            /           0    0 4   c_account_type_details_cc_account_type_detail_id_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq OWNED BY pit_lifebank_account.account_type_details.cc_account_type_detail_id;
            pit_lifebank_account       postgres    false    205            �            1259    68490    type_accounts    TABLE     �   CREATE TABLE pit_lifebank_account.type_accounts (
    cc_type_account_id integer NOT NULL,
    cc_type_account_name character varying(15)
);
 /   DROP TABLE pit_lifebank_account.type_accounts;
       pit_lifebank_account         postgres    false    6            �            1259    68488 &   c_type_accounts_cc_type_account_id_seq    SEQUENCE     �   CREATE SEQUENCE pit_lifebank_account.c_type_accounts_cc_type_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 K   DROP SEQUENCE pit_lifebank_account.c_type_accounts_cc_type_account_id_seq;
       pit_lifebank_account       postgres    false    203    6            0           0    0 &   c_type_accounts_cc_type_account_id_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE pit_lifebank_account.c_type_accounts_cc_type_account_id_seq OWNED BY pit_lifebank_account.type_accounts.cc_type_account_id;
            pit_lifebank_account       postgres    false    202            �            1259    68496    client_accounts    TABLE     �   CREATE TABLE pit_lifebank_account.client_accounts (
    cc_client_account_id character varying(30) NOT NULL,
    cc_client_account_client_id character varying(30) NOT NULL,
    cc_client_type_account_id integer,
    cc_amount numeric
);
 1   DROP TABLE pit_lifebank_account.client_accounts;
       pit_lifebank_account         postgres    false    6            �            1259    68483    clients    TABLE     �   CREATE TABLE pit_lifebank_account.clients (
    cc_client_id character varying(30) NOT NULL,
    cc_client_name character varying(30)
);
 )   DROP TABLE pit_lifebank_account.clients;
       pit_lifebank_account         postgres    false    6            �
           2604    68471    usr_user_session uus_session_id    DEFAULT     �   ALTER TABLE ONLY pit_lifebank.usr_user_session ALTER COLUMN uus_session_id SET DEFAULT nextval('pit_lifebank.usr_user_session_uus_session_id_seq'::regclass);
 T   ALTER TABLE pit_lifebank.usr_user_session ALTER COLUMN uus_session_id DROP DEFAULT;
       pit_lifebank       postgres    false    200    199    200            �
           2604    68532 .   account_type_details cc_account_type_detail_id    DEFAULT     �   ALTER TABLE ONLY pit_lifebank_account.account_type_details ALTER COLUMN cc_account_type_detail_id SET DEFAULT nextval('pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq'::regclass);
 k   ALTER TABLE pit_lifebank_account.account_type_details ALTER COLUMN cc_account_type_detail_id DROP DEFAULT;
       pit_lifebank_account       postgres    false    206    205    206            �
           2604    68493     type_accounts cc_type_account_id    DEFAULT     �   ALTER TABLE ONLY pit_lifebank_account.type_accounts ALTER COLUMN cc_type_account_id SET DEFAULT nextval('pit_lifebank_account.c_type_accounts_cc_type_account_id_seq'::regclass);
 ]   ALTER TABLE pit_lifebank_account.type_accounts ALTER COLUMN cc_type_account_id DROP DEFAULT;
       pit_lifebank_account       postgres    false    203    202    203            !          0    68468    usr_user_session 
   TABLE DATA                     pit_lifebank       postgres    false    200   �                 0    68367 	   usr_users 
   TABLE DATA                     pit_lifebank       postgres    false    198   �
       (          0    68550    account_trasactions 
   TABLE DATA                     pit_lifebank_account       postgres    false    207   L       '          0    68529    account_type_details 
   TABLE DATA                     pit_lifebank_account       postgres    false    206   )       %          0    68496    client_accounts 
   TABLE DATA                     pit_lifebank_account       postgres    false    204   �       "          0    68483    clients 
   TABLE DATA                     pit_lifebank_account       postgres    false    201   h       $          0    68490    type_accounts 
   TABLE DATA                     pit_lifebank_account       postgres    false    203   �       1           0    0 #   usr_user_session_uus_session_id_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('pit_lifebank.usr_user_session_uus_session_id_seq', 56, true);
            pit_lifebank       postgres    false    199            2           0    0 4   c_account_type_details_cc_account_type_detail_id_seq    SEQUENCE SET     p   SELECT pg_catalog.setval('pit_lifebank_account.c_account_type_details_cc_account_type_detail_id_seq', 6, true);
            pit_lifebank_account       postgres    false    205            3           0    0 &   c_type_accounts_cc_type_account_id_seq    SEQUENCE SET     b   SELECT pg_catalog.setval('pit_lifebank_account.c_type_accounts_cc_type_account_id_seq', 4, true);
            pit_lifebank_account       postgres    false    202            �
           2606    68481 $   usr_user_session usr_user_session_pk 
   CONSTRAINT     t   ALTER TABLE ONLY pit_lifebank.usr_user_session
    ADD CONSTRAINT usr_user_session_pk PRIMARY KEY (uus_session_id);
 T   ALTER TABLE ONLY pit_lifebank.usr_user_session DROP CONSTRAINT usr_user_session_pk;
       pit_lifebank         postgres    false    200            �
           2606    68444    usr_users usr_users_pk 
   CONSTRAINT     e   ALTER TABLE ONLY pit_lifebank.usr_users
    ADD CONSTRAINT usr_users_pk PRIMARY KEY (usr_user_name);
 F   ALTER TABLE ONLY pit_lifebank.usr_users DROP CONSTRAINT usr_users_pk;
       pit_lifebank         postgres    false    198            �
           2606    68557    account_trasactions newtable_pk 
   CONSTRAINT     x   ALTER TABLE ONLY pit_lifebank_account.account_trasactions
    ADD CONSTRAINT newtable_pk PRIMARY KEY (t_trasaction_id);
 W   ALTER TABLE ONLY pit_lifebank_account.account_trasactions DROP CONSTRAINT newtable_pk;
       pit_lifebank_account         postgres    false    207            �
           2606    68500 $   client_accounts pk_client_account_id 
   CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.client_accounts
    ADD CONSTRAINT pk_client_account_id PRIMARY KEY (cc_client_account_id);
 \   ALTER TABLE ONLY pit_lifebank_account.client_accounts DROP CONSTRAINT pk_client_account_id;
       pit_lifebank_account         postgres    false    204            �
           2606    68487    clients pk_client_id 
   CONSTRAINT     j   ALTER TABLE ONLY pit_lifebank_account.clients
    ADD CONSTRAINT pk_client_id PRIMARY KEY (cc_client_id);
 L   ALTER TABLE ONLY pit_lifebank_account.clients DROP CONSTRAINT pk_client_id;
       pit_lifebank_account         postgres    false    201            �
           2606    68495     type_accounts pk_type_account_id 
   CONSTRAINT     |   ALTER TABLE ONLY pit_lifebank_account.type_accounts
    ADD CONSTRAINT pk_type_account_id PRIMARY KEY (cc_type_account_id);
 X   ALTER TABLE ONLY pit_lifebank_account.type_accounts DROP CONSTRAINT pk_type_account_id;
       pit_lifebank_account         postgres    false    203            �
           2606    68534 &   account_type_details pk_type_detail_id 
   CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.account_type_details
    ADD CONSTRAINT pk_type_detail_id PRIMARY KEY (cc_account_type_detail_id);
 ^   ALTER TABLE ONLY pit_lifebank_account.account_type_details DROP CONSTRAINT pk_type_detail_id;
       pit_lifebank_account         postgres    false    206            �
           2606    68475 $   usr_user_session usr_user_session_fk    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank.usr_user_session
    ADD CONSTRAINT usr_user_session_fk FOREIGN KEY (usr_user_name_id) REFERENCES pit_lifebank.usr_users(usr_user_name) ON UPDATE CASCADE ON DELETE CASCADE;
 T   ALTER TABLE ONLY pit_lifebank.usr_user_session DROP CONSTRAINT usr_user_session_fk;
       pit_lifebank       postgres    false    200    2708    198            �
           2606    68540 $   client_accounts c_client_accounts_fk    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.client_accounts
    ADD CONSTRAINT c_client_accounts_fk FOREIGN KEY (cc_client_type_account_id) REFERENCES pit_lifebank_account.account_type_details(cc_account_type_detail_id) ON UPDATE CASCADE ON DELETE CASCADE;
 \   ALTER TABLE ONLY pit_lifebank_account.client_accounts DROP CONSTRAINT c_client_accounts_fk;
       pit_lifebank_account       postgres    false    2718    206    204            �
           2606    68545 "   client_accounts client_accounts_fk    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.client_accounts
    ADD CONSTRAINT client_accounts_fk FOREIGN KEY (cc_client_account_client_id) REFERENCES pit_lifebank_account.clients(cc_client_id) ON UPDATE CASCADE ON DELETE CASCADE;
 Z   ALTER TABLE ONLY pit_lifebank_account.client_accounts DROP CONSTRAINT client_accounts_fk;
       pit_lifebank_account       postgres    false    2712    204    201            �
           2606    68535 &   account_type_details fk_type_detail_id    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.account_type_details
    ADD CONSTRAINT fk_type_detail_id FOREIGN KEY (cc_account_type_detail_type_id) REFERENCES pit_lifebank_account.type_accounts(cc_type_account_id);
 ^   ALTER TABLE ONLY pit_lifebank_account.account_type_details DROP CONSTRAINT fk_type_detail_id;
       pit_lifebank_account       postgres    false    206    2714    203            �
           2606    68559    account_trasactions newtable_fk    FK CONSTRAINT     �   ALTER TABLE ONLY pit_lifebank_account.account_trasactions
    ADD CONSTRAINT newtable_fk FOREIGN KEY (t_trasaction_account_id) REFERENCES pit_lifebank_account.client_accounts(cc_client_account_id) ON UPDATE CASCADE ON DELETE CASCADE;
 W   ALTER TABLE ONLY pit_lifebank_account.account_trasactions DROP CONSTRAINT newtable_fk;
       pit_lifebank_account       postgres    false    204    2716    207           