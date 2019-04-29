/*
 Navicat Premium Data Transfer

 Source Server         : BookStore
 Source Server Type    : SQLite
 Source Server Version : 3021000
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3021000
 File Encoding         : 65001

 Date: 29/04/2019 21:36:41
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS "book";
CREATE TABLE "book" (
  "id" integer PRIMARY KEY AUTOINCREMENT,
  "author" text,
  "name" text,
  "press" text,
  "price" real,
  UNIQUE ("name" ASC)
);

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO "book" VALUES (1, 'Mahmoud', 'Data Algorithms', 'O''REILLY', 128.0);
INSERT INTO "book" VALUES (3, 'google', 'Linux', 'google', 123.0);
INSERT INTO "book" VALUES (5, 'Wdiss', 'JAVA', 'China', 99.0);

-- ----------------------------
-- Auto increment value for book
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 5 WHERE name = 'book';

PRAGMA foreign_keys = true;
