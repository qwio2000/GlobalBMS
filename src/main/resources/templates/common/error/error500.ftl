<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>JEI-GLOBAL &gt; 홈페이지 내부 오류</title>
	<style type="text/css">
		/* reset.css */
			html,body,div,span,p,h1,h2,h3,h4,h5,h6,ul,ol,li,dl,dt,dd,label,a,strong,img,fieldset,form{margin:0;padding:0;}
			body, h1,h2,h3,h4,h5,h6,input,button,textarea,select,table,p{font-size:14px;font-family:'dotum','돋움','sans-serif';line-height:1.2em}
			table {width:100%;table-layout:fixed;border-collapse: collapse;border-spacing: 0;}
			caption{width:0;height:0;font-size:0;line-height:0;overflow:hidden}
			address,em{font-style:normal}
			img,fieldset,iframe{border:0 none}
			img,input,select,button,textarea{vertical-align:middle;}
			label,button,input[type="button"],input[type="submit"]{cursor:pointer;border:0;outline:0;}
			input,textarea,select{font-size:inherit;font-family:inherit}

			input{padding:0;margin:0;border:0;font-family:'dotum','돋움','sans-serif';line-height:1.2em;font-size:14px;overflow:visible;background:transparent}
			button{padding:0;margin:0;border:0;cursor:pointer;line-height:0;vertical-align:top;overflow:visible;font-size:12px;font-family:'dotum','돋움','sans-serif';line-height:1.2em;background:transparent;outline: none;}
			button::-moz-focus-inner,input::-moz-focus-inner, button:active{border:0;padding:0;outline: none;}

			hr{display:none;}
			a{text-decoration:none;color:#48494b;}
			a:hover{text-decoration:underline}
			a:active{background-color:transparent;}
			a:active, a:selected, a:visited, a:focus {border: none;outline: none;}

		/*layout*/
			body, html{width:100%;height:100%}
			body{position:relative;z-index:1}
		/* error500 */
			.error500{width:500px;margin:0 auto;margin-top:100px;}
			.error500 h1{font-size:25px;line-height:50px}
			.error500 .error_notice{border-top:1px solid #eee;padding:40px 20px;background:#fBFBFB}
			.error500 .error_notice h2{font-size:20px;line-height:30px;font-family:"Malgun Gothic","맑은고딕"}
			.error500 .error_notice .txt{padding:20px 0}
			.error500 .error_notice .txt p{font-size:12px;line-height:1.5em}
			.error500 .error_notice .link{text-align:center;padding-top:5px}
			.error500 .error_notice .link a{display:inline-block;padding:0 16px;line-height:42px;background:#68A0E7;margin:0 5px;border-radius:5px;color:#fff;border:1px solid #366DCE;font-weight:bold;font-size:13px;text-decoration:none;}
			.error500 .error_notice .link a:hover{background:#366DCE}
			.error500 .copyright{padding-top:10px;border-top:1px solid #eee;text-align:center;font-size:12px}
	</style>
</head>
<body>
	<div class="wrap">
		<div class="error500">
			<h1><a href="/">JEI-GLOBAL</a></h1>
			<div class="error_notice">
				<h2>이용에 불편을 드려 죄송합니다. <br /> 잠시 후 이용해 주시기 바랍니다.</h2>
				<div class="txt">
					<p>Error Code : ${error?default('') }, ${message?default('') }</p>
					<p>요청한 페이지 내부에서 오류가 발생했기 때문에 페이지를 표시할 수 없습니다.</p>
					<p>안정적인 시스템으로 보다 나은 서비스를 제공하도록 노력하겠습니다.</p>
					<p>감사합니다.</p>
				</div>
				<div class="link">
					<a href="javascript:history.back();">이전페이지로 이동</a><a href="/">JEI-GLOBAL 첫화면으로 이동</a>
				</div>
			</div>
			<div class="copyright">
				copyright &copy; JEI corporation. All rights reserved.
			</div>
		</div>
	</div>
</body>
</html>