package com.lijia.filedb;

import java.util.LinkedHashMap;

class _table {
		private Class _class;
		private String name;
		private String unicode;
		private String schema;
		private int length;
		private LinkedHashMap<String, _column> columns = new LinkedHashMap<String, _column>();
		private _column pk;
		public String getName() {
			return name;
		}

		public Class get_class() {
			return _class;
		}

		public void set_class(Class _class) {
			this._class = _class;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUnicode() {
			return unicode;
		}

		public void setUnicode(String unicode) {
			this.unicode = unicode;
		}

		public String getSchema() {
			return schema;
		}

		public void setSchema(String schema) {
			this.schema = schema;
		}

		public LinkedHashMap<String, _column> getColumns() {
			return columns;
		}

		public void setColumns(LinkedHashMap<String, _column> columns) {
			this.columns = columns;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public _column getPk() {
			return pk;
		}

		public void setPk(_column pk) {
			this.pk = pk;
		}

	}