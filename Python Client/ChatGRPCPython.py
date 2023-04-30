import grpc
import chat_pb2 as chat_pb2
import chat_pb2_grpc as chat_pb2_grpc

class ChatClient:
     def __init__(self, host, port):
         self.channel = grpc.insecure_channel(f"{host}:{port}")
         self.stub = chat_pb2_grpc.ChatServiceStub(self.channel)
         self.username = None

     def join_chat(self):
         while not self.username:
             self.username = input("Enter your username: ")
         message = chat_pb2.Message(username=self.username)
         self.stub.SendMessage(message)

     def send_message(self):
         while True:
             text = input("> ")
             if text == "/quit":
                 break
             message = chat_pb2.Message(username=self.username, text=text)
             self.stub.SendMessage(message)


if __name__ == "__main__":
    client = ChatClient("localhost", 5555)
    client.join_chat()
    client.send_message()
