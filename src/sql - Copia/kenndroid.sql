-- Script de Criação de Estrutura de Banco do KennDroid
-- por: Renata Sena

-- Tabela de Canis
CREATE TABLE IF NOT EXISTS
Canil (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	nome			TEXT		NOT NULL,
	estado			TEXT		NULL,
	cidade			TEXT		NULL,
	endereco		TEXT		NULL,
	complemento		TEXT		NULL,
	ponto_ref		TEXT		NULL,
	cep				INTEGER		NULL,
	data_fundacao	TEXT		NULL,
	cnpj			TEXT		NULL,
	razao_social	TEXT		NULL,
	fone1			TEXT		NULL,
	fone2			TEXT		NULL,
	email			TEXT		NULL
);

-- Tabela de Animais
CREATE TABLE IF NOT EXISTS
Animal (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	nome			TEXT		NOT NULL,
	data_nasc		TEXT		NULL,
	pai				INTEGER		NOT NULL,
	mae				INTEGER		NOT NULL,
	canil			INTEGER		NOT NULL,
	comprador		INTEGER		NULL,
	data_venda		TEXT		NULL,
	foto			BLOB		NULL
);

-- Tabela de Vendas
CREATE TABLE IF NOT EXISTS
Venda (
	id						INTEGER		PRIMARY KEY		AUTOINCREMENT,
	id_Animal				INTEGER		NOT NULL,
	id_Cliente				INTEGER		NOT NULL,
	data					TEXT		NULL,
	valor					INTEGER		NULL,
	valor_recebido			INTEGER		NULL,
	data_ult_pagamento		TEXT		NULL,
	data_entrega_pedigree	TEXT		NULL,
	forma_pagamento			INTEGER		NULL,
	nota_fiscal				INTEGER		NULL,
	observacoes				TEXT		NULL
	);

-- Tabela de Clientes
CREATE TABLE IF NOT EXISTS
Cliente (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	nome			TEXT		NOT NULL,
	dia_nasc		TEXT		NULL,
	mes_nasc		TEXT		NULL,
	ano_nasc		TEXT		NULL,
	cidade			TEXT		NULL,
	estado			TEXT		NULL,
	endereco		TEXT		NULL,
	complemento		TEXT		NULL,
	ponto_ref		TEXT		NULL,
	cep				INTEGER		NULL,
	cpf				TEXT		NULL,
	fone1			TEXT		NULL,
	fone2			TEXT		NULL,
	email			TEXT		NULL
);

-- Tabela de Clinicas
CREATE TABLE IF NOT EXISTS
Clinica (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	cnpj			TEXT		NULL,
	nome			TEXT		NOT NULL,
	razao_social	TEXT		NULL,
	cidade			TEXT		NULL,
	estado			TEXT		NULL,
	endereco		TEXT		NULL,
	complemento		TEXT		NULL,
	ponto_ref		TEXT		NULL,
	cep				INTEGER		NULL,
	fone1			TEXT		NULL,
	fone2			TEXT		NULL,
	email			TEXT		NULL
);

-- Tabela de Veterinarios
CREATE TABLE IF NOT EXISTS
Veterinario (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	cpf				TEXT		NULL,
	nome			TEXT		NOT NULL,
	especialidade	TEXT		NULL,
	cidade			TEXT		NULL,
	estado			TEXT		NULL,
	endereco		TEXT		NULL,
	complemento		TEXT		NULL,
	ponto_ref		TEXT		NULL,
	cep				INTEGER		NULL,
	fone1			TEXT		NULL,
	fone2			TEXT		NULL,
	email			TEXT		NULL
);

-- Tabela de cadastro das Vacinas
CREATE TABLE IF NOT EXISTS
Vacina (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	nome			TEXT		NOT NULL,
	descricao		TEXT		NULL
);

-- Tabela de Vacinacoes realizadas
CREATE TABLE IF NOT EXISTS
Vacinacao (
	id					INTEGER		PRIMARY KEY		AUTOINCREMENT,
	id_Animal			INTEGER		NOT NULL,
	id_Veterinario		INTEGER		NOT NULL,
	id_Clinica			INTEGER		NOT NULL,
	data 				TEXT		NOT NULL,
	marca				TEXT		NULL,
	validade			TEXT 		NULL,
	lote				TEXT		NULL,
	reacoes_adversas	TEXT		NULL,
	valor 				INTEGER		NOT NULL,
	observacoes			TEXT		NULL
);

-- Tabela de Eventos
CREATE TABLE IF NOT EXISTS
Evento (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	tipo			INTEGER		NULL,
	nome			TEXT		NOT NULL,
	cidade			TEXT		NULL,
	estado			TEXT		NULL,
	endereco		TEXT		NULL,
	complemento		TEXT		NULL,
	ponto_ref		TEXT		NULL,
	cep				INTEGER		NULL,
	fone			TEXT		NULL,
	email			TEXT		NULL,
	organizacao		TEXT		NULL,
	data 			TEXT		NULL
);


-- Tabela de registro de eventos em que animais do Canil participaram
CREATE TABLE IF NOT EXISTS
AnimalEvento (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	id_Evento		INTEGER		NOT NULL,
	categoria		TEXT		NULL,
	classificacao	TEXT		NULL,
	pontos			TEXT		NULL,
	juizes			TEXT		NULL,
	penalizacao		INTEGER		NULL,
	percursos		INTEGER		NULL
);

-- Tabela de acasalamentos do Canil
CREATE TABLE IF NOT EXISTS
Cruzamento (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	macho			INTEGER		NOT NULL,
	femea			INTEGER		NOT NULL,
	data			TEXT		NULL,
	nome_local		TEXT		NULL,
	cidade			TEXT		NULL,
	estado			TEXT		NULL,
	endereco		TEXT		NULL,
	complemento		TEXT		NULL,
	ponto_ref		TEXT		NULL,
	cep				INTEGER		NULL,
	sucesso			INTEGER		NULL
);


-- Tabela de registro das configuracoes
CREATE TABLE IF NOT EXISTS
Config (
	id				INTEGER		PRIMARY KEY		AUTOINCREMENT,
	id_Canil		INTEGER		NOT NULL,
	senha			TEXT		NULL
);
