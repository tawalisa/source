/**
 * 
 */
var iLocalization = {
		_data : new Object(),
			
	    __XmlHttpPool__ : 
	    { 
	    	m_async : false,
	        m_MaxPoolLength : 10, 
	        m_XmlHttpPool : [], 
	         
	        __requestObject : function() 
	        { 
	            var xmlhttp = null; 
	            var pool = this.m_XmlHttpPool; 
	            for ( var i=0 ; i < pool.length ; ++i ) 
	            { 
	                if ( pool[i].readyState == 4 || pool[i].readyState == 0 ) 
	                { 
	                    xmlhttp = pool[i]; 
	                    break; 
	                } 
	            } 
	            if ( xmlhttp == null ) 
	            { 
	                return this.__extendPool(); 
	            } 
	            return xmlhttp; 
	        }, 
	         
	        __extendPool : function() 
	        { 
	            if ( this.m_XmlHttpPool.length < this.m_MaxPoolLength ) 
	            { 
	                var xmlhttp = null; 
	                xmlhttp =  window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : new XMLHttpRequest();
	               
	                if ( xmlhttp ) 
	                { 
	                    this.m_XmlHttpPool.push(xmlhttp); 
	                } 
	                return xmlhttp; 
	            } 
	        }, 
	         
	        GetRemoteData : function(url, callback) 
	        { 
	            this.__receiveRemoteData(url, callback, 'GET', null); 
	        }, 
	         
	        PostRemoteData : function(url, callback, data) 
	        { 
	            this.__receiveRemoteData(url, callback, 'POST', data); 
	        }, 
	         
	        __receiveRemoteData : function(url, callback, httpmethod, data) 
	        { 
	            var xmlhttp = this.__requestObject(); 
	            if ( !xmlhttp ) 
	            { 
	                return null; 
	            } 
	            xmlhttp.open(httpmethod, url, this.m_async); 
	            xmlhttp.onreadystatechange = function() 
	            { 
	                if ( xmlhttp.readyState == 4 || xmlhttp.readyState == 'complete' ) 
	                { 
	                    callback(xmlhttp.responseText); 
	                } 
	            }; 
	            xmlhttp.send(data); 
	        } 
	    },
		getPrepertiseString: function(resource,key){
			var that = this;
			var innerkey =this._replaceAll(resource,"[.]","_");
			var retu="";
			value = eval("this._data."+innerkey);
			if(!value){
				this.__XmlHttpPool__.PostRemoteData("/servlet/localization?resource="+resource,function(data){
					if(data && data.length>0){
						//alert(data);
						var json = eval('('+data+')');
						retu = json;
						eval("that._data."+innerkey+" = retu; ");
						//alert(that._data);
						//alert(that._data.com_founder_enp_dicmgr_WebMessage);
						
					}
					//alert(2);
					
				});
			}else{
				return eval("value."+key);
			}
			return eval("retu."+key);
		},
		_replaceAll : function( string,s1,s2) {
		    return string.replace(new RegExp(s1,"gm"),s2);
		},
		init:function(){
			this._data = new Object();
		}
		
}
iLocalization.init();
