CREATE DATABASE Carnaval
go
USE Carnaval

CREATE TABLE Quesito
(
 id int NOT NULL IDENTITY PRIMARY KEY,
 nome VARCHAR(100) NOT NULL
)

CREATE TABLE Jurado
(
 id int NOT NULL IDENTITY PRIMARY KEY,
 nome VARCHAR(100) NOT NULL
)

CREATE TABLE QuesitoJurado
(
 id_quesito int NOT NULL,
 id_jurado int NOT NULL,
 posicao int NOT NULL, 
 FOREIGN KEY (id_quesito) REFERENCES Quesito(id),
 FOREIGN KEY (id_jurado) REFERENCES Jurado(id),
 PRIMARY KEY (id_quesito, id_jurado)
)

CREATE TABLE Escola
(
 id int NOT NULL IDENTITY PRIMARY KEY,
 nome VARCHAR(100) NOT NULL,
 totalPontos DECIMAL(7,1)  
)

CREATE TABLE Tabela
(
 id_escola int NOT NULL,
 id_quesito int NOT NULL,
 nota1 DECIMAL(7,1),
 nota2 DECIMAL(7,1),
 nota3 DECIMAL(7,1),
 nota4 DECIMAL(7,1),
 nota5 DECIMAL(7,1),
 menor DECIMAL(7,1),
 maior DECIMAL(7,1),
 nota_total DECIMAL(7,1),
 PRIMARY KEY (id_escola,id_quesito)
)

INSERT INTO Tabela VALUES (1,1,4.0,5.0,6.0,NULL,NULL,NULL,NULL,nota_total)

INSERT INTO Quesito VALUES ('Comissão de Frente'),
       ('Evolução'),
       ('Fantasia'),
       ('Bateria'),
       ('Alegoria'),
       ('Harmonia'),
       ('Samba-Enredo'),
       ('Mestre-Sala e Porta-Bandeira'),
       ('Enredo')

INSERT INTO Escola(nome) VALUES
	   ('Acadêmicos do Tatuapé'),
       ('Rosas de Ouro'),
       ('Mancha Verde'),
       ('Vai-Vai'),
       ('X-9 Paulistana'),
       ('Dragões da Real'),
       ('Aguia de Ouro'),
       ('Nenê de Vila Matilde'),
       ('Gaviões de Fiel'),
       ('Mocidade Alegre'),
       ('Tom Maior'),
       ('Unidos de Vila Maria'),
       ('Acadêmicos do Tucuruvi'),
       ('Império de Casa Verde')

INSERT INTO Jurado VALUES
	('Bolsonaro'),
	('Dilma'),
	('Japones da Federal'),
	('Kefera'),	
	('Nando Moura'),
	('PC Siqueira'),
	('Keiichi'),
	('Cristina'),
	('Colevatinho'),
	('Dollynho'),
	('Carlos Alberto'),
	('Goleiro Bruno'),
	('Chimbinha'),
	('Zeca Pagodinho'),
	('Detonator')

INSERT INTO QuesitoJurado Values
(1, 1, 1),
(1, 2, 2),
(1, 3, 3),
(1, 4, 4),
(1, 5, 5),
(2, 6, 1),
(2, 7, 2),
(2, 8, 3),
(2, 9, 4),
(2, 10, 5),
(3, 11, 1),
(3, 12, 2),
(3, 13, 3),
(3, 14, 4),
(3, 15, 5),
(4, 1, 1),
(4, 2, 2),
(4, 3, 3),
(4, 4, 4),
(4, 5, 5),
(5, 6, 1),
(5, 7, 2),
(5, 8, 3),
(5, 9, 4),
(5, 10, 5),
(6, 11, 1),
(6, 12, 2),
(6, 13, 3),
(6, 14, 4),
(6, 15, 5),
(7, 1, 1),
(7, 2, 2),
(7, 3, 3),
(7, 4, 4),
(7, 5, 5),
(8, 6, 1),
(8, 7, 2),
(8, 8, 3),
(8, 9, 4),
(8, 10, 5),
(9, 11, 1),
(9, 12, 2),
(9, 13, 3),
(9, 14, 4),
(9, 15, 5)

SELECT * FROM Quesito
SELECT * FROM Escola
SELECT * FROM Jurado
SELECT * FROM QuesitoJurado

SELECT q.nome Quesito, j.nome Jurado, posicao NBR FROM QuesitoJurado qj
INNER JOIN Quesito q
ON qj.id_quesito = q.id
INNER JOIN Jurado j
ON qj.id_jurado = j.id


EXEC sp_notas_str 'Acadêmicos do Tatuapé','Bolsonaro','Comissão de Frente',5.0
EXEC sp_notas_str 'Acadêmicos do Tatuapé','Dilma','Comissão de Frente',6.0
EXEC sp_notas_str 'Acadêmicos do Tatuapé','Japones da Federal','Comissão de Frente',7.0
EXEC sp_notas_str 'Acadêmicos do Tatuapé','Kefera','Comissão de Frente',8.0
EXEC sp_notas_str 'Acadêmicos do Tatuapé','Nando Moura','Comissão de Frente',9.0
SELECT * FROM Escola
SELECT * FROM Tabela
EXEC sp_notas 1,1,1,5.0
EXEC sp_notas 1,1,2,6.0
EXEC sp_notas 1,1,3,7.0
EXEC sp_notas 1,1,4,8.0
EXEC sp_notas 1,1,5,9.0

drop procedure sp_notas_str

delete from tabela

EXEC sp_total 'Acadêmicos do Tatuapé'

CREATE PROCEDURE sp_total(@nomeescola VARCHAR(100))
AS
	DECLARE @idescola INT, @total DECIMAL(7,1)
	SET @idescola = (SELECT id FROM Escola WHERE nome = @nomeescola);
	SET @total = (SELECT SUM(nota_total) FROM Tabela WHERE id_escola = @idescola);
	UPDATE Escola SET totalPontos = @total WHERE id = @idescola


CREATE PROCEDURE sp_notas_str(@nomeescola VARCHAR(100), @nomejurado VARCHAR(100), @nomequesito VARCHAR(100), @nota DECIMAL(7,1))
AS
	DECLARE @posicao INT, @menor DECIMAL(7,1), @maior DECIMAL(7,1), @notatotal DECIMAL(7,1), @nota1 DECIMAL(7,1), @nota2 DECIMAL(7,1), @nota3 DECIMAL(7,1), @nota4 DECIMAL(7,1), @idquesito INT, @idescola INT, @idjurado INT
	SET @idescola = (SELECT id FROM Escola WHERE nome = @nomeescola);
	print(@idescola);
	SET @idjurado = (SELECT id FROM Jurado WHERE nome = @nomejurado);
	print(@idjurado);
	SET @idquesito = (SELECT id FROM Quesito WHERE nome = @nomequesito);
	SET @posicao = (SELECT posicao FROM QuesitoJurado WHERE id_quesito = @idquesito AND id_jurado = @idjurado);
	print(@idquesito);
	print(@nota);
	print(@posicao);
	IF (@posicao = 1)
	BEGIN
		INSERT INTO tabela (id_escola, id_quesito, nota1)
		VALUES (@idescola, @idquesito, @nota)
	END
	IF(@posicao = 2)
	BEGIN
		UPDATE tabela SET nota2 = @nota WHERE id_escola = @idescola and id_quesito = @idquesito;
	END
	IF(@posicao = 3)
	BEGIN
		SET @nota1 = (SELECT nota1 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota2 = (SELECT nota2 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		IF(@nota1>@nota2)
		BEGIN
			SET @menor = @nota2;
			SET @maior = @nota1;
		END
		ELSE
		BEGIN
			SET @menor = @nota1;
			SET @maior = @nota2;
		END
		IF(@nota>@maior)
		BEGIN
			SET @maior = @nota;
		END
		IF(@nota<@menor)
		BEGIN
			SET @menor = @nota;
		END
		SET @notatotal = (@nota1 + @nota2 + @nota) - (@maior + @menor);
		UPDATE tabela SET nota3 = @nota, menor = @menor, maior = @maior, nota_total = @notatotal WHERE id_escola = @idescola and id_quesito = @idquesito;
	END
	IF(@posicao = 4)
	BEGIN
		SET @nota1 = (SELECT nota1 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota2 = (SELECT nota2 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota3 = (SELECT nota3 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @menor = (SELECT menor FROM tabela WHERE id_escola = @idescola AND id_quesito = @idquesito);
		SET @maior = (SELECT maior FROM tabela WHERE id_escola = @idescola AND id_quesito = @idquesito);
		IF(@nota>@maior)
		BEGIN
			SET	@maior = @nota;
		END
		IF(@nota<@menor)
		BEGIN
			SET	@menor = @nota;
		END
		SET @notatotal = (@nota1 + @nota2 + @nota3 + @nota) - (@maior + @menor);
		UPDATE tabela SET nota4 = @nota, menor = @menor, maior = @maior, nota_total = @notatotal WHERE id_escola = @idescola and id_quesito = @idquesito;
	END
	IF(@posicao = 5)
	BEGIN
		SET @nota1 = (SELECT nota1 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota2 = (SELECT nota2 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota3 = (SELECT nota3 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota4 = (SELECT nota4 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @menor = (SELECT menor FROM tabela WHERE id_escola = @idescola AND id_quesito = @idquesito);
		SET @maior = (SELECT maior FROM tabela WHERE id_escola = @idescola AND id_quesito = @idquesito);
		IF(@nota>@maior)
		BEGIN
			SET @maior = @nota;
		END
		IF(@nota<@menor)
		BEGIN
			SET @menor = @nota;
		END
		SET @notatotal = (@nota1 + @nota2 + @nota3 + @nota4 + @nota) - (@maior + @menor);
		UPDATE tabela SET nota5 = @nota, menor = @menor, maior = @maior, nota_total = @notatotal WHERE id_escola = @idescola and id_quesito = @idquesito;
	END




	
CREATE PROCEDURE sp_notas(@idquesito INT, @idescola INT, @idjurado INT, @nota DECIMAL(7,1))
AS
	DECLARE @posicao INT, @menor DECIMAL(7,1), @maior DECIMAL(7,1), @notatotal DECIMAL(7,1), @nota1 DECIMAL(7,1), @nota2 DECIMAL(7,1), @nota3 DECIMAL(7,1), @nota4 DECIMAL(7,1)
	SET @posicao = (SELECT posicao FROM QuesitoJurado WHERE id_quesito = @idquesito AND id_jurado = @idjurado);
	IF (@posicao = 1)
	BEGIN
		INSERT INTO tabela (id_escola, id_quesito, nota1)
		VALUES (@idescola, @idquesito, @nota)
	END
	IF(@posicao = 2)
	BEGIN
		UPDATE tabela SET nota2 = @nota WHERE id_escola = @idescola and id_quesito = @idquesito;
	END
	IF(@posicao = 3)
	BEGIN
		SET @nota1 = (SELECT nota1 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota2 = (SELECT nota2 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		IF(@nota1>@nota2)
		BEGIN
			SET @menor = @nota2;
			SET @maior = @nota1;
		END
		ELSE
		BEGIN
			SET @menor = @nota1;
			SET @maior = @nota2;
		END
		IF(@nota>@maior)
		BEGIN
			SET @maior = @nota;
		END
		IF(@nota<@menor)
		BEGIN
			SET @menor = @nota;
		END
		SET @notatotal = (@nota1 + @nota2 + @nota) - (@maior + @menor);
		UPDATE tabela SET nota3 = @nota, menor = @menor, maior = @maior, nota_total = @notatotal WHERE id_escola = @idescola and id_quesito = @idquesito;
	END
	IF(@posicao = 4)
	BEGIN
		SET @nota1 = (SELECT nota1 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota2 = (SELECT nota2 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota3 = (SELECT nota3 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @menor = (SELECT menor FROM tabela WHERE id_escola = @idescola AND id_quesito = @idquesito);
		SET @maior = (SELECT maior FROM tabela WHERE id_escola = @idescola AND id_quesito = @idquesito);
		IF(@nota>@maior)
		BEGIN
			SET	@maior = @nota;
		END
		IF(@nota<@menor)
		BEGIN
			SET	@menor = @nota;
		END
		SET @notatotal = (@nota1 + @nota2 + @nota3 + @nota) - (@maior + @menor);
		UPDATE tabela SET nota4 = @nota, menor = @menor, maior = @maior, nota_total = @notatotal WHERE id_escola = @idescola and id_quesito = @idquesito;
	END
	IF(@posicao = 5)
	BEGIN
		SET @nota1 = (SELECT nota1 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota2 = (SELECT nota2 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota3 = (SELECT nota3 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @nota4 = (SELECT nota4 from tabela WHERE id_escola = @idescola and id_quesito = @idquesito);
		SET @menor = (SELECT menor FROM tabela WHERE id_escola = @idescola AND id_quesito = @idquesito);
		SET @maior = (SELECT maior FROM tabela WHERE id_escola = @idescola AND id_quesito = @idquesito);
		IF(@nota>@maior)
		BEGIN
			SET @maior = @nota;
		END
		IF(@nota<@menor)
		BEGIN
			SET @menor = @nota;
		END
		SET @notatotal = (@nota1 + @nota2 + @nota3 + @nota4 + @nota) - (@maior + @menor);
		UPDATE tabela SET nota5 = @nota, menor = @menor, maior = @maior, nota_total = @notatotal WHERE id_escola = @idescola and id_quesito = @idquesito;
	END