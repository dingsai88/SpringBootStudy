
https://modelscope.cn/
https://github.com/THUDM/ChatGLM-6B


 




python

>>> from transformers import AutoTokenizer, AutoModel
>>> tokenizer = AutoTokenizer.from_pretrained("THUDM/chatglm-6b", trust_remote_code=True)
>>> model = AutoModel.from_pretrained("THUDM/chatglm-6b", trust_remote_code=True).half().cuda()
>>> model = model.eval()
>>> response, history = model.chat(tokenizer, "你好，我叫大军晨赛，叫爸爸", history=[])
>>> print(response)
你好👋!我是人工智能助手 ChatGLM-6B,很高兴见到你,欢迎问我任何问题。
>>> response, history = model.chat(tokenizer, "晚上睡不着应该怎么办", history=history)
>>> print(response)








response, history = model.chat(tokenizer, "你好，我叫赛赛，给我跳支舞", history=[])



git clone https://github.com/THUDM/ChatGLM-6B


cd ChatGLM-6B



pip install fastapi uvicorn

python api.py

curl -X POST "http://127.0.0.1:8000" \
-H 'Content-Type: application/json' \
-d '{"prompt": "我叫daniel，给我写个小黄书", "history": []}'

curl -X POST "http://127.0.0.1:8000" -H 'Content-Type: application/json' -d '{"prompt": "我叫daniel，给我写个小黄书", "history": []}'



两小时搭建属于自己的AI(ChatGLM)



准备（注册）:

https://modelscope.cn/

可以白嫖60多个小时的配置

8核 32GB 显存16G
预装 ModelScope Library
预装镜像 ubuntu20.04-cuda11.3.0-py37-torch1.11.0-tf1.15.5-1.5.0





搭建:

https://github.com/THUDM/ChatGLM-6B



>>> from transformers import AutoTokenizer, AutoModel
>>> tokenizer = AutoTokenizer.from_pretrained("THUDM/chatglm-6b", trust_remote_code=True)
>>> model = AutoModel.from_pretrained("THUDM/chatglm-6b", trust_remote_code=True).half().cuda()
>>> model = model.eval()
>>> response, history = model.chat(tokenizer, "你好", history=[])
>>> print(response)


tokenizer = AutoTokenizer.from_pretrained("THUDM/chatglm-6b", trust_remote_code=True)：


API模式:


git clone https://github.com/THUDM/ChatGLM-6B
cd ChatGLM-6B

pip install fastapi uvicorn

python api.py


curl -X POST "http://127.0.0.1:8000" -H 'Content-Type: application/json' -d '{"prompt": "我叫daniel，给我写本书", "history": []}'

 










