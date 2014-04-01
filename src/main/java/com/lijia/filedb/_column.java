package com.lijia.filedb;

import com.lijia.filedb.annotation.Type;

class _column {
		private String name;
		private boolean isid;
		private Type type;
		private int length;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isIsid() {
			return isid;
		}

		public void setIsid(boolean isid) {
			this.isid = isid;
		}

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

	}