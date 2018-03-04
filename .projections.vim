{
  "src/**/*.clj": {"alternate": "test/**/{}_test.clj"},
  "test/**/*_test.clj": {"alternate": "src/**/{}.clj"}
  "resources/**/*.up.sql": {"alternate": "resources/**/{}.down.sql"},
  "resources/**/*.down.sql": {"alternate": "resources/**/{}.up.sql"}
}
