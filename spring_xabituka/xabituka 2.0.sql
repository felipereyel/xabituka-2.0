CREATE TABLE "users" (
  "id" SERIAL PRIMARY KEY,
  "nickname" varchar,
  "full_name" varchar,
  "photo" varchar,
  "created_at" timestamp,
  "psswd" varchar
);

CREATE TABLE "groups" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "photo" varchar,
  "owner_id" int,
  "created_at" timestamp,
  "description" varchar
);

CREATE TABLE "user_group" (
  "id" SERIAL PRIMARY KEY,
  "user_id" int,
  "group_id" int,
  "joined_at" timestamp,
  "exited_at" timestamp DEFAULT null
);

CREATE TABLE "messages" (
  "id" SERIAL PRIMARY KEY,
  "content" varchar,
  "user_group_id" int,
  "created_at" timestamp
);

ALTER TABLE "groups" ADD FOREIGN KEY ("owner_id") REFERENCES "users" ("id");

ALTER TABLE "user_group" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "user_group" ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("id");

ALTER TABLE "messages" ADD FOREIGN KEY ("user_group_id") REFERENCES "user_group" ("id");
