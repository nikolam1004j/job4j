--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.12
-- Dumped by pg_dump version 9.6.12

-- Started on 2019-03-20 17:28:45

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE servlets;
--
-- TOC entry 2178 (class 1262 OID 16384)
-- Name: servlets; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE servlets WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';


ALTER DATABASE servlets OWNER TO postgres;

\connect servlets

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 16385)
-- Name: crudsrvlet; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA crudsrvlet;


ALTER SCHEMA crudsrvlet OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 190 (class 1259 OID 16401)
-- Name: passwords; Type: TABLE; Schema: crudsrvlet; Owner: postgres
--

CREATE TABLE crudsrvlet.passwords (
                                    id integer NOT NULL,
                                    user_id integer NOT NULL,
                                    expired timestamp without time zone,
                                    pass character varying(25)
);


ALTER TABLE crudsrvlet.passwords OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16397)
-- Name: passwords_id_seq; Type: SEQUENCE; Schema: crudsrvlet; Owner: postgres
--

CREATE SEQUENCE crudsrvlet.passwords_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE crudsrvlet.passwords_id_seq OWNER TO postgres;

--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 188
-- Name: passwords_id_seq; Type: SEQUENCE OWNED BY; Schema: crudsrvlet; Owner: postgres
--

ALTER SEQUENCE crudsrvlet.passwords_id_seq OWNED BY crudsrvlet.passwords.id;


--
-- TOC entry 189 (class 1259 OID 16399)
-- Name: passwords_user_id_seq; Type: SEQUENCE; Schema: crudsrvlet; Owner: postgres
--

CREATE SEQUENCE crudsrvlet.passwords_user_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE crudsrvlet.passwords_user_id_seq OWNER TO postgres;

--
-- TOC entry 2183 (class 0 OID 0)
-- Dependencies: 189
-- Name: passwords_user_id_seq; Type: SEQUENCE OWNED BY; Schema: crudsrvlet; Owner: postgres
--

ALTER SEQUENCE crudsrvlet.passwords_user_id_seq OWNED BY crudsrvlet.passwords.user_id;


--
-- TOC entry 192 (class 1259 OID 16431)
-- Name: roles; Type: TABLE; Schema: crudsrvlet; Owner: postgres
--

CREATE TABLE crudsrvlet.roles (
                                id integer NOT NULL,
                                name character varying(20)
);


ALTER TABLE crudsrvlet.roles OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16429)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: crudsrvlet; Owner: postgres
--

CREATE SEQUENCE crudsrvlet.roles_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE crudsrvlet.roles_id_seq OWNER TO postgres;

--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 191
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: crudsrvlet; Owner: postgres
--

ALTER SEQUENCE crudsrvlet.roles_id_seq OWNED BY crudsrvlet.roles.id;


--
-- TOC entry 196 (class 1259 OID 16467)
-- Name: user_roles; Type: TABLE; Schema: crudsrvlet; Owner: postgres
--

CREATE TABLE crudsrvlet.user_roles (
                                     id integer NOT NULL,
                                     user_id integer NOT NULL,
                                     role_id integer NOT NULL
);


ALTER TABLE crudsrvlet.user_roles OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 16461)
-- Name: user_roles_id_seq; Type: SEQUENCE; Schema: crudsrvlet; Owner: postgres
--

CREATE SEQUENCE crudsrvlet.user_roles_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE crudsrvlet.user_roles_id_seq OWNER TO postgres;

--
-- TOC entry 2185 (class 0 OID 0)
-- Dependencies: 193
-- Name: user_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: crudsrvlet; Owner: postgres
--

ALTER SEQUENCE crudsrvlet.user_roles_id_seq OWNED BY crudsrvlet.user_roles.id;


--
-- TOC entry 195 (class 1259 OID 16465)
-- Name: user_roles_role_id_seq; Type: SEQUENCE; Schema: crudsrvlet; Owner: postgres
--

CREATE SEQUENCE crudsrvlet.user_roles_role_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE crudsrvlet.user_roles_role_id_seq OWNER TO postgres;

--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 195
-- Name: user_roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: crudsrvlet; Owner: postgres
--

ALTER SEQUENCE crudsrvlet.user_roles_role_id_seq OWNED BY crudsrvlet.user_roles.role_id;


--
-- TOC entry 194 (class 1259 OID 16463)
-- Name: user_roles_user_id_seq; Type: SEQUENCE; Schema: crudsrvlet; Owner: postgres
--

CREATE SEQUENCE crudsrvlet.user_roles_user_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE crudsrvlet.user_roles_user_id_seq OWNER TO postgres;

--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 194
-- Name: user_roles_user_id_seq; Type: SEQUENCE OWNED BY; Schema: crudsrvlet; Owner: postgres
--

ALTER SEQUENCE crudsrvlet.user_roles_user_id_seq OWNED BY crudsrvlet.user_roles.user_id;


--
-- TOC entry 187 (class 1259 OID 16388)
-- Name: users; Type: TABLE; Schema: crudsrvlet; Owner: postgres
--

CREATE TABLE crudsrvlet.users (
                                id integer NOT NULL,
                                name character varying(500),
                                login character varying(500),
                                email character varying(500),
                                create_date timestamp without time zone
);


ALTER TABLE crudsrvlet.users OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16386)
-- Name: users_id_seq; Type: SEQUENCE; Schema: crudsrvlet; Owner: postgres
--

CREATE SEQUENCE crudsrvlet.users_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER TABLE crudsrvlet.users_id_seq OWNER TO postgres;

--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 186
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: crudsrvlet; Owner: postgres
--

ALTER SEQUENCE crudsrvlet.users_id_seq OWNED BY crudsrvlet.users.id;


--
-- TOC entry 2028 (class 2604 OID 16404)
-- Name: passwords id; Type: DEFAULT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.passwords ALTER COLUMN id SET DEFAULT nextval('crudsrvlet.passwords_id_seq'::regclass);


--
-- TOC entry 2029 (class 2604 OID 16405)
-- Name: passwords user_id; Type: DEFAULT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.passwords ALTER COLUMN user_id SET DEFAULT nextval('crudsrvlet.passwords_user_id_seq'::regclass);


--
-- TOC entry 2030 (class 2604 OID 16434)
-- Name: roles id; Type: DEFAULT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.roles ALTER COLUMN id SET DEFAULT nextval('crudsrvlet.roles_id_seq'::regclass);


--
-- TOC entry 2031 (class 2604 OID 16470)
-- Name: user_roles id; Type: DEFAULT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.user_roles ALTER COLUMN id SET DEFAULT nextval('crudsrvlet.user_roles_id_seq'::regclass);


--
-- TOC entry 2032 (class 2604 OID 16471)
-- Name: user_roles user_id; Type: DEFAULT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.user_roles ALTER COLUMN user_id SET DEFAULT nextval('crudsrvlet.user_roles_user_id_seq'::regclass);


--
-- TOC entry 2033 (class 2604 OID 16472)
-- Name: user_roles role_id; Type: DEFAULT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.user_roles ALTER COLUMN role_id SET DEFAULT nextval('crudsrvlet.user_roles_role_id_seq'::regclass);


--
-- TOC entry 2027 (class 2604 OID 16391)
-- Name: users id; Type: DEFAULT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.users ALTER COLUMN id SET DEFAULT nextval('crudsrvlet.users_id_seq'::regclass);


--
-- TOC entry 2166 (class 0 OID 16401)
-- Dependencies: 190
-- Data for Name: passwords; Type: TABLE DATA; Schema: crudsrvlet; Owner: postgres
--

INSERT INTO crudsrvlet.passwords (id, user_id, expired, pass) VALUES (3, 2, '2019-06-01 23:59:59', '12345');
INSERT INTO crudsrvlet.passwords (id, user_id, expired, pass) VALUES (4, 5, '2019-07-01 23:59:59', 'qwerty');
INSERT INTO crudsrvlet.passwords (id, user_id, expired, pass) VALUES (5, 6, '2019-03-19 23:59:59', 'pass');


--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 188
-- Name: passwords_id_seq; Type: SEQUENCE SET; Schema: crudsrvlet; Owner: postgres
--

SELECT pg_catalog.setval('crudsrvlet.passwords_id_seq', 5, true);


--
-- TOC entry 2190 (class 0 OID 0)
-- Dependencies: 189
-- Name: passwords_user_id_seq; Type: SEQUENCE SET; Schema: crudsrvlet; Owner: postgres
--

SELECT pg_catalog.setval('crudsrvlet.passwords_user_id_seq', 1, false);


--
-- TOC entry 2168 (class 0 OID 16431)
-- Dependencies: 192
-- Data for Name: roles; Type: TABLE DATA; Schema: crudsrvlet; Owner: postgres
--

INSERT INTO crudsrvlet.roles (id, name) VALUES (1, 'admin');
INSERT INTO crudsrvlet.roles (id, name) VALUES (2, 'moder');
INSERT INTO crudsrvlet.roles (id, name) VALUES (3, 'user');


--
-- TOC entry 2191 (class 0 OID 0)
-- Dependencies: 191
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: crudsrvlet; Owner: postgres
--

SELECT pg_catalog.setval('crudsrvlet.roles_id_seq', 3, true);


--
-- TOC entry 2172 (class 0 OID 16467)
-- Dependencies: 196
-- Data for Name: user_roles; Type: TABLE DATA; Schema: crudsrvlet; Owner: postgres
--

INSERT INTO crudsrvlet.user_roles (id, user_id, role_id) VALUES (2, 5, 3);
INSERT INTO crudsrvlet.user_roles (id, user_id, role_id) VALUES (3, 6, 3);
INSERT INTO crudsrvlet.user_roles (id, user_id, role_id) VALUES (4, 7, 3);
INSERT INTO crudsrvlet.user_roles (id, user_id, role_id) VALUES (5, 9, 3);
INSERT INTO crudsrvlet.user_roles (id, user_id, role_id) VALUES (6, 11, 3);
INSERT INTO crudsrvlet.user_roles (id, user_id, role_id) VALUES (7, 14, 3);
INSERT INTO crudsrvlet.user_roles (id, user_id, role_id) VALUES (1, 2, 1);


--
-- TOC entry 2192 (class 0 OID 0)
-- Dependencies: 193
-- Name: user_roles_id_seq; Type: SEQUENCE SET; Schema: crudsrvlet; Owner: postgres
--

SELECT pg_catalog.setval('crudsrvlet.user_roles_id_seq', 7, true);


--
-- TOC entry 2193 (class 0 OID 0)
-- Dependencies: 195
-- Name: user_roles_role_id_seq; Type: SEQUENCE SET; Schema: crudsrvlet; Owner: postgres
--

SELECT pg_catalog.setval('crudsrvlet.user_roles_role_id_seq', 14, true);


--
-- TOC entry 2194 (class 0 OID 0)
-- Dependencies: 194
-- Name: user_roles_user_id_seq; Type: SEQUENCE SET; Schema: crudsrvlet; Owner: postgres
--

SELECT pg_catalog.setval('crudsrvlet.user_roles_user_id_seq', 1, false);


--
-- TOC entry 2163 (class 0 OID 16388)
-- Dependencies: 187
-- Data for Name: users; Type: TABLE DATA; Schema: crudsrvlet; Owner: postgres
--

INSERT INTO crudsrvlet.users (id, name, login, email, create_date) VALUES (2, 'nick', 'nickraz', 'nickraz@yandex.ru', '2019-03-18 16:48:20');
INSERT INTO crudsrvlet.users (id, name, login, email, create_date) VALUES (5, 'Дима', 'Lbvf', 'nicky6677@mail.ru', '2019-03-18 16:49:21');
INSERT INTO crudsrvlet.users (id, name, login, email, create_date) VALUES (6, 'Аня', 'Anna', 'anna@mail.ru', '2019-03-18 16:51:19');
INSERT INTO crudsrvlet.users (id, name, login, email, create_date) VALUES (7, 'gosha', 'gosha', 'gosha@yandex.ru', '2019-03-19 10:04:21');
INSERT INTO crudsrvlet.users (id, name, login, email, create_date) VALUES (9, 'ira', 'ira', 'ira@yandex.ru', '2019-03-19 10:16:21');
INSERT INTO crudsrvlet.users (id, name, login, email, create_date) VALUES (11, 'kirill', 'kirill', 'kirill@yandex.ru', '2019-03-19 10:23:05');
INSERT INTO crudsrvlet.users (id, name, login, email, create_date) VALUES (14, 'vera', 'vera', 'vera@yandex.ru', '2019-03-19 10:28:39');


--
-- TOC entry 2195 (class 0 OID 0)
-- Dependencies: 186
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: crudsrvlet; Owner: postgres
--

SELECT pg_catalog.setval('crudsrvlet.users_id_seq', 14, true);


--
-- TOC entry 2037 (class 2606 OID 16407)
-- Name: passwords passwords_pkey; Type: CONSTRAINT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.passwords
  ADD CONSTRAINT passwords_pkey PRIMARY KEY (id);


--
-- TOC entry 2039 (class 2606 OID 16436)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.roles
  ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2041 (class 2606 OID 16474)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.user_roles
  ADD CONSTRAINT user_roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2035 (class 2606 OID 16396)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.users
  ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2042 (class 2606 OID 16408)
-- Name: passwords passwords_user_id_fkey; Type: FK CONSTRAINT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.passwords
  ADD CONSTRAINT passwords_user_id_fkey FOREIGN KEY (user_id) REFERENCES crudsrvlet.users(id);


--
-- TOC entry 2044 (class 2606 OID 16480)
-- Name: user_roles user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.user_roles
  ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES crudsrvlet.roles(id);


--
-- TOC entry 2043 (class 2606 OID 16475)
-- Name: user_roles user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: crudsrvlet; Owner: postgres
--

ALTER TABLE ONLY crudsrvlet.user_roles
  ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES crudsrvlet.users(id);


--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-03-20 17:28:47

--
-- PostgreSQL database dump complete
--

