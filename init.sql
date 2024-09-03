-- Create table for component
CREATE TABLE component (
    name VARCHAR(255) NOT NULL,
    formula VARCHAR(255) NOT NULL,
    mm FLOAT(53) NOT NULL,
    rho_ci FLOAT(53) NOT NULL,
    t_ci FLOAT(53) NOT NULL,
    PRIMARY KEY (name)
);




-- Create table for reduced_mix_variables
CREATE TABLE reduced_mix_variables (
    name VARCHAR(255) NOT NULL,
    beta_tij FLOAT(53) NOT NULL,
    beta_vij FLOAT(53) NOT NULL,
    gamma_tij FLOAT(53) NOT NULL,
    gamma_vij FLOAT(53) NOT NULL,
    PRIMARY KEY (name)
);


-- Create table for bicombination
CREATE TABLE bicombination (
    name VARCHAR(255) NOT NULL,
    f_ij FLOAT(53) NOT NULL,
    fk_bicomb VARCHAR(255),
    PRIMARY KEY (name),
    CONSTRAINT FK_bicomb_reduced_mix FOREIGN KEY (fk_bicomb) REFERENCES reduced_mix_variables(name)
);


-- Create join table for many-to-many relationship between component and bicombination
CREATE TABLE componentbi (
    component_id VARCHAR(255) NOT NULL,
    combination_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (component_id, combination_id),
    CONSTRAINT FK_component_bi_component FOREIGN KEY (component_id) REFERENCES component(name),
    CONSTRAINT FK_component_bi_bicombination FOREIGN KEY (combination_id) REFERENCES bicombination(name)
);


-- Create table for alpha_ideal_oi
CREATE TABLE alpha_ideal_oi (
    id BIGINT NOT NULL,
    k INTEGER NOT NULL,
    n_oik FLOAT(53),
    name VARCHAR(255) NOT NULL,
    teta_oik FLOAT(53),
    fk_component VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_alpha_ideal_component FOREIGN KEY (fk_component) REFERENCES component(name)
);

-- Create table for alpha_res_ij
CREATE TABLE alpha_res_ij (
    id BIGINT NOT NULL,
    beta_ijk FLOAT(53) NOT NULL,
    d_ijk FLOAT(53) NOT NULL,
    episilon_ijk FLOAT(53) NOT NULL,
    gama_ijk FLOAT(53) NOT NULL,
    k INTEGER NOT NULL,
    n_ijk FLOAT(53) NOT NULL,
    t_ijk FLOAT(53) NOT NULL,
    fk_bi VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_alpha_res_bi FOREIGN KEY (fk_bi) REFERENCES bicombination(name)
);

-- Create table for alpha_res_oi
CREATE TABLE alpha_res_oi (
    id BIGINT NOT NULL,
    c_oik FLOAT(53) NOT NULL,
    d_oik FLOAT(53) NOT NULL,
    k INTEGER NOT NULL,
    n_oik FLOAT(53) NOT NULL,
    t_oik FLOAT(53) NOT NULL,
    fk_component VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_alpha_res_component FOREIGN KEY (fk_component) REFERENCES component(name)
);


-- Create sequences for auto-incrementing IDs
CREATE SEQUENCE alpha_ideal_oi_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE alpha_res_ij_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE alpha_res_oi_seq START WITH 1 INCREMENT BY 50;


-- Components CO2 and Pentane
INSERT INTO component (name, formula, mm, rho_ci, t_ci)
VALUES('Carbon Dioxide', 'CO2', 0.04400950000000001, 10624.978697999999, 304.128);

INSERT INTO component (name, formula, mm, rho_ci, t_ci)
VALUES('Pentane', 'nC5H12', 0.07214878000000001, 3215.577588, 469.7);


-- reduced variables parameters co2 pentane
INSERT INTO reduced_mix_variables
(name, beta_tij, beta_vij, gamma_tij, gamma_vij)
VALUES('CO2nC5H12', 1.027000795, 1.024311498, 0.979217302, 1.068406078);


-- Combination CO2 and Pentanes
INSERT INTO bicombination (name, f_ij, fk_bicomb)
VALUES('CO2nC5H12', 0.0, 'CO2nC5H12');


INSERT INTO componentbi (component_id, combination_id)
VALUES('Pentane', 'CO2nC5H12');

-- Component-Bicombination relationship for CO2 and Pentane
INSERT INTO componentbi (component_id, combination_id)
VALUES('Carbon Dioxide', 'CO2nC5H12');


-- Alpha Ideal OI for CO2 and Pentane
INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3202, 1, 11.925152758, 'Carbon Dioxide', 0.0, 'Carbon Dioxide');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3203, 2, -16.118762264, 'Carbon Dioxide', 0.0, 'Carbon Dioxide');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3204, 3, 2.50002, 'Carbon Dioxide', 0.0, 'Carbon Dioxide');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3205, 4, 2.04452, 'Carbon Dioxide', 3.022758166, 'Carbon Dioxide');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3206, 5, -1.06044, 'Carbon Dioxide', -2.844425476, 'Carbon Dioxide');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3207, 6, 2.03366, 'Carbon Dioxide', 1.589964364, 'Carbon Dioxide');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3208, 7, 0.01393, 'Carbon Dioxide', 1.12159609, 'Carbon Dioxide');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3209, 1, 14.536611217, 'Pentane', 0.0, 'Pentane');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3210, 2, -89.919548319, 'Pentane', 0.0, 'Pentane');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3211, 3, 3.0, 'Pentane', 0.0, 'Pentane');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3212, 4, 8.95043, 'Pentane', 0.380391739, 'Pentane');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3213, 5, 21.836, 'Pentane', 1.789520971, 'Pentane');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3214, 6, 33.4032, 'Pentane', 3.777411113, 'Pentane');

INSERT INTO alpha_ideal_oi (id, k, n_oik, name, teta_oik, fk_component)
VALUES(3215, 7, 0.0, 'Pentane', 0.0, 'Pentane');

-- Alpha Res OI for CO2 and Pentane
INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3202, 0.0, 1.0, 1, 0.52646564804653, 0.0, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3203, 0.0, 1.0, 2, -1.4995725042592, 1.25, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3204, 0.0, 2.0, 3, 0.27329786733782, 1.625, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3205, 0.0, 3.0, 4, 0.12949500022786, 0.375, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3206, 1.0, 3.0, 5, 0.15404088341841, 0.375, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3207, 1.0, 3.0, 6, -0.58186950946814, 1.375, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3208, 1.0, 4.0, 7, -0.18022494838296, 1.125, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3209, 1.0, 5.0, 8, -0.095389904072812, 1.375, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3210, 1.0, 6.0, 9, -0.008048681931767901, 0.125, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3211, 1.0, 6.0, 10, -0.035547751273090004, 1.625, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3212, 2.0, 1.0, 11, -0.28079014882405, 3.75, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3213, 2.0, 4.0, 12, -0.08243589008167701, 3.5, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3214, 3.0, 1.0, 13, 0.010832427979006, 7.5, 'Carbon Dioxide');

INSERT INTO alpha_res_oi (id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3215, 3.0, 1.0, 14, -0.0067073993161097, 8.0, 'Carbon Dioxide');

-- Insert statements for Carbon Dioxide
INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3216, 3.0, 3.0, 15, -0.0046827907600524, 6.0, 'Carbon Dioxide');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3217, 3.0, 3.0, 16, -0.028359911832177, 16.0, 'Carbon Dioxide');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3218, 3.0, 4.0, 17, 0.019500174744098, 11.0, 'Carbon Dioxide');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3219, 5.0, 5.0, 18, -0.21609137507166, 24.0, 'Carbon Dioxide');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3220, 5.0, 5.0, 19, 0.43772794926972, 26.0, 'Carbon Dioxide');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3221, 5.0, 5.0, 20, -0.22130790113593, 28.0, 'Carbon Dioxide');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3222, 6.0, 5.0, 21, 0.015190189957330999, 24.0, 'Carbon Dioxide');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3223, 6.0, 5.0, 22, -0.0153809489533, 26.0, 'Carbon Dioxide');

-- Insert statements for Pentane
INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3224, 0.0, 1.0, 1, 1.0968643098001, 0.25, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3225, 0.0, 1.0, 2, -2.9988888298061003, 1.125, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3226, 0.0, 1.0, 3, 0.99516886799212, 1.5, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3227, 0.0, 2.0, 4, -0.16170708558539, 1.375, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3228, 0.0, 3.0, 5, 0.11334460072775, 0.25, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3229, 0.0, 7.0, 6, 0.0002676059515075, 0.875, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3230, 1.0, 2.0, 7, 0.40979881986931, 0.625, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3231, 1.0, 5.0, 8, -0.040876423083075, 1.75, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3232, 2.0, 1.0, 9, -0.38169482469447, 3.625, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3233, 2.0, 4.0, 10, -0.10931956843993, 3.625, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3234, 3.0, 3.0, 11, -0.03207322332799, 14.5, 'Pentane');

INSERT INTO public.alpha_res_oi
(id, c_oik, d_oik, k, n_oik, t_oik, fk_component)
VALUES(3235, 3.0, 4.0, 12, 0.016877016216975, 12.0, 'Pentane');







