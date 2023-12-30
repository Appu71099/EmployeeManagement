package com.demo.employee.dto;

public class ApiResponse <T>{

	 private T data;
	    private int status;
	    
	    public ApiResponse() {
			super();
			// TODO Auto-generated constructor stub
		}


		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}


		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public ApiResponse(T data, int status) {
	        this.data = data;
	        this.status = status;
	    }
}
