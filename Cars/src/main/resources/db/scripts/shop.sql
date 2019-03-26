--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.12
-- Dumped by pg_dump version 9.6.12

-- Started on 2019-03-27 16:10:53

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
-- TOC entry 8 (class 2615 OID 17069)
-- Name: carshop; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA carshop;


ALTER SCHEMA carshop OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 205 (class 1259 OID 17070)
-- Name: car; Type: TABLE; Schema: carshop; Owner: postgres
--

CREATE TABLE carshop.car (
                             id integer NOT NULL,
                             model character varying(100),
                             cuzov_id integer NOT NULL,
                             dvigatel_id integer NOT NULL,
                             corobka_id integer NOT NULL,
                             link character varying(2000),
                             price numeric,
                             sold boolean DEFAULT false,
                             owner character varying(500)
);


ALTER TABLE carshop.car OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 17077)
-- Name: car_corobka_id_seq; Type: SEQUENCE; Schema: carshop; Owner: postgres
--

CREATE SEQUENCE carshop.car_corobka_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE carshop.car_corobka_id_seq OWNER TO postgres;

--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 206
-- Name: car_corobka_id_seq; Type: SEQUENCE OWNED BY; Schema: carshop; Owner: postgres
--

ALTER SEQUENCE carshop.car_corobka_id_seq OWNED BY carshop.car.corobka_id;


--
-- TOC entry 207 (class 1259 OID 17079)
-- Name: car_cuzov_id_seq; Type: SEQUENCE; Schema: carshop; Owner: postgres
--

CREATE SEQUENCE carshop.car_cuzov_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE carshop.car_cuzov_id_seq OWNER TO postgres;

--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 207
-- Name: car_cuzov_id_seq; Type: SEQUENCE OWNED BY; Schema: carshop; Owner: postgres
--

ALTER SEQUENCE carshop.car_cuzov_id_seq OWNED BY carshop.car.cuzov_id;


--
-- TOC entry 208 (class 1259 OID 17081)
-- Name: car_dvigatel_id_seq; Type: SEQUENCE; Schema: carshop; Owner: postgres
--

CREATE SEQUENCE carshop.car_dvigatel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE carshop.car_dvigatel_id_seq OWNER TO postgres;

--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 208
-- Name: car_dvigatel_id_seq; Type: SEQUENCE OWNED BY; Schema: carshop; Owner: postgres
--

ALTER SEQUENCE carshop.car_dvigatel_id_seq OWNED BY carshop.car.dvigatel_id;


--
-- TOC entry 209 (class 1259 OID 17083)
-- Name: car_id_seq; Type: SEQUENCE; Schema: carshop; Owner: postgres
--

CREATE SEQUENCE carshop.car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE carshop.car_id_seq OWNER TO postgres;

--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 209
-- Name: car_id_seq; Type: SEQUENCE OWNED BY; Schema: carshop; Owner: postgres
--

ALTER SEQUENCE carshop.car_id_seq OWNED BY carshop.car.id;


--
-- TOC entry 210 (class 1259 OID 17085)
-- Name: corobka; Type: TABLE; Schema: carshop; Owner: postgres
--

CREATE TABLE carshop.corobka (
                                 id integer NOT NULL,
                                 automatic boolean
);


ALTER TABLE carshop.corobka OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 17088)
-- Name: corobka_id_seq; Type: SEQUENCE; Schema: carshop; Owner: postgres
--

CREATE SEQUENCE carshop.corobka_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE carshop.corobka_id_seq OWNER TO postgres;

--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 211
-- Name: corobka_id_seq; Type: SEQUENCE OWNED BY; Schema: carshop; Owner: postgres
--

ALTER SEQUENCE carshop.corobka_id_seq OWNED BY carshop.corobka.id;


--
-- TOC entry 212 (class 1259 OID 17090)
-- Name: cuzov; Type: TABLE; Schema: carshop; Owner: postgres
--

CREATE TABLE carshop.cuzov (
                               id integer NOT NULL,
                               name character varying(500)
);


ALTER TABLE carshop.cuzov OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 17093)
-- Name: cuzov_id_seq; Type: SEQUENCE; Schema: carshop; Owner: postgres
--

CREATE SEQUENCE carshop.cuzov_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE carshop.cuzov_id_seq OWNER TO postgres;

--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 213
-- Name: cuzov_id_seq; Type: SEQUENCE OWNED BY; Schema: carshop; Owner: postgres
--

ALTER SEQUENCE carshop.cuzov_id_seq OWNED BY carshop.cuzov.id;


--
-- TOC entry 214 (class 1259 OID 17095)
-- Name: dvigatel; Type: TABLE; Schema: carshop; Owner: postgres
--

CREATE TABLE carshop.dvigatel (
                                  id integer NOT NULL,
                                  name character varying(500),
                                  maxspeed integer
);


ALTER TABLE carshop.dvigatel OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17101)
-- Name: dvigatel_id_seq; Type: SEQUENCE; Schema: carshop; Owner: postgres
--

CREATE SEQUENCE carshop.dvigatel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE carshop.dvigatel_id_seq OWNER TO postgres;

--
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 215
-- Name: dvigatel_id_seq; Type: SEQUENCE OWNED BY; Schema: carshop; Owner: postgres
--

ALTER SEQUENCE carshop.dvigatel_id_seq OWNED BY carshop.dvigatel.id;


--
-- TOC entry 2077 (class 2604 OID 17103)
-- Name: car id; Type: DEFAULT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.car ALTER COLUMN id SET DEFAULT nextval('carshop.car_id_seq'::regclass);


--
-- TOC entry 2078 (class 2604 OID 17104)
-- Name: car cuzov_id; Type: DEFAULT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.car ALTER COLUMN cuzov_id SET DEFAULT nextval('carshop.car_cuzov_id_seq'::regclass);


--
-- TOC entry 2079 (class 2604 OID 17105)
-- Name: car dvigatel_id; Type: DEFAULT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.car ALTER COLUMN dvigatel_id SET DEFAULT nextval('carshop.car_dvigatel_id_seq'::regclass);


--
-- TOC entry 2080 (class 2604 OID 17106)
-- Name: car corobka_id; Type: DEFAULT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.car ALTER COLUMN corobka_id SET DEFAULT nextval('carshop.car_corobka_id_seq'::regclass);


--
-- TOC entry 2081 (class 2604 OID 17107)
-- Name: corobka id; Type: DEFAULT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.corobka ALTER COLUMN id SET DEFAULT nextval('carshop.corobka_id_seq'::regclass);


--
-- TOC entry 2082 (class 2604 OID 17108)
-- Name: cuzov id; Type: DEFAULT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.cuzov ALTER COLUMN id SET DEFAULT nextval('carshop.cuzov_id_seq'::regclass);


--
-- TOC entry 2083 (class 2604 OID 17109)
-- Name: dvigatel id; Type: DEFAULT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.dvigatel ALTER COLUMN id SET DEFAULT nextval('carshop.dvigatel_id_seq'::regclass);


--
-- TOC entry 2212 (class 0 OID 17070)
-- Dependencies: 205
-- Data for Name: car; Type: TABLE DATA; Schema: carshop; Owner: postgres
--

INSERT INTO carshop.car (id, model, cuzov_id, dvigatel_id, corobka_id, link, price, sold, owner) VALUES (1, 'BMW', 1, 3, 1, 'img/bmw.jpg', 25000, true, 'Николай Разилов');
INSERT INTO carshop.car (id, model, cuzov_id, dvigatel_id, corobka_id, link, price, sold, owner) VALUES (2, 'OPEL', 1, 2, 2, 'img/opel.jpg', 45000, false, 'Анна Разилова');
INSERT INTO carshop.car (id, model, cuzov_id, dvigatel_id, corobka_id, link, price, sold, owner) VALUES (8, 'WOLKSVAGEN', 1, 3, 2, 'img/wolks.jpg', 30000, true, 'Денис Мироненко');
INSERT INTO carshop.car (id, model, cuzov_id, dvigatel_id, corobka_id, link, price, sold, owner) VALUES (9, 'MITSUBISHI', 1, 3, 2, 'img/mits.jpg', 35000, false, 'Александра Ремезова');
INSERT INTO carshop.car (id, model, cuzov_id, dvigatel_id, corobka_id, link, price, sold, owner) VALUES (31, 'BMW M3', 1, 3, 1, 'img/bmwm3.jpg', 80000, true, 'Дмитрий Бегин');


--
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 206
-- Name: car_corobka_id_seq; Type: SEQUENCE SET; Schema: carshop; Owner: postgres
--

SELECT pg_catalog.setval('carshop.car_corobka_id_seq', 1, false);


--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 207
-- Name: car_cuzov_id_seq; Type: SEQUENCE SET; Schema: carshop; Owner: postgres
--

SELECT pg_catalog.setval('carshop.car_cuzov_id_seq', 1, false);


--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 208
-- Name: car_dvigatel_id_seq; Type: SEQUENCE SET; Schema: carshop; Owner: postgres
--

SELECT pg_catalog.setval('carshop.car_dvigatel_id_seq', 1, false);


--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 209
-- Name: car_id_seq; Type: SEQUENCE SET; Schema: carshop; Owner: postgres
--

SELECT pg_catalog.setval('carshop.car_id_seq', 31, true);


--
-- TOC entry 2217 (class 0 OID 17085)
-- Dependencies: 210
-- Data for Name: corobka; Type: TABLE DATA; Schema: carshop; Owner: postgres
--

INSERT INTO carshop.corobka (id, automatic) VALUES (1, true);
INSERT INTO carshop.corobka (id, automatic) VALUES (2, false);


--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 211
-- Name: corobka_id_seq; Type: SEQUENCE SET; Schema: carshop; Owner: postgres
--

SELECT pg_catalog.setval('carshop.corobka_id_seq', 2, true);


--
-- TOC entry 2219 (class 0 OID 17090)
-- Dependencies: 212
-- Data for Name: cuzov; Type: TABLE DATA; Schema: carshop; Owner: postgres
--

INSERT INTO carshop.cuzov (id, name) VALUES (1, 'Нет кузова');
INSERT INTO carshop.cuzov (id, name) VALUES (2, 'Маленький кузов');
INSERT INTO carshop.cuzov (id, name) VALUES (3, 'Обычный кузов');
INSERT INTO carshop.cuzov (id, name) VALUES (4, 'Большой кузов');


--
-- TOC entry 2240 (class 0 OID 0)
-- Dependencies: 213
-- Name: cuzov_id_seq; Type: SEQUENCE SET; Schema: carshop; Owner: postgres
--

SELECT pg_catalog.setval('carshop.cuzov_id_seq', 4, true);


--
-- TOC entry 2221 (class 0 OID 17095)
-- Dependencies: 214
-- Data for Name: dvigatel; Type: TABLE DATA; Schema: carshop; Owner: postgres
--

INSERT INTO carshop.dvigatel (id, name, maxspeed) VALUES (1, 'Медленный двигатель', 60);
INSERT INTO carshop.dvigatel (id, name, maxspeed) VALUES (2, 'Средний двигатель', 120);
INSERT INTO carshop.dvigatel (id, name, maxspeed) VALUES (3, 'Мощный двигатель', 240);


--
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 215
-- Name: dvigatel_id_seq; Type: SEQUENCE SET; Schema: carshop; Owner: postgres
--

SELECT pg_catalog.setval('carshop.dvigatel_id_seq', 3, true);


--
-- TOC entry 2085 (class 2606 OID 17111)
-- Name: car car_pkey; Type: CONSTRAINT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id);


--
-- TOC entry 2087 (class 2606 OID 17113)
-- Name: corobka corobka_pkey; Type: CONSTRAINT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.corobka
    ADD CONSTRAINT corobka_pkey PRIMARY KEY (id);


--
-- TOC entry 2089 (class 2606 OID 17115)
-- Name: cuzov cuzov_pkey; Type: CONSTRAINT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.cuzov
    ADD CONSTRAINT cuzov_pkey PRIMARY KEY (id);


--
-- TOC entry 2091 (class 2606 OID 17117)
-- Name: dvigatel dvigatel_pkey; Type: CONSTRAINT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.dvigatel
    ADD CONSTRAINT dvigatel_pkey PRIMARY KEY (id);


--
-- TOC entry 2092 (class 2606 OID 17118)
-- Name: car car_corobka_id_fkey; Type: FK CONSTRAINT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.car
    ADD CONSTRAINT car_corobka_id_fkey FOREIGN KEY (corobka_id) REFERENCES carshop.corobka(id);


--
-- TOC entry 2093 (class 2606 OID 17123)
-- Name: car car_cuzov_id_fkey; Type: FK CONSTRAINT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.car
    ADD CONSTRAINT car_cuzov_id_fkey FOREIGN KEY (cuzov_id) REFERENCES carshop.cuzov(id);


--
-- TOC entry 2094 (class 2606 OID 17128)
-- Name: car car_dvigatel_id_fkey; Type: FK CONSTRAINT; Schema: carshop; Owner: postgres
--

ALTER TABLE ONLY carshop.car
    ADD CONSTRAINT car_dvigatel_id_fkey FOREIGN KEY (dvigatel_id) REFERENCES carshop.dvigatel(id);


-- Completed on 2019-03-27 16:10:54

--
-- PostgreSQL database dump complete
--

