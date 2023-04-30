from concurrent import futures
import game_pb2 as guess_number_game_pb2
import game_pb2_grpc as guess_number_game_pb2_grpc
import grpc
import sys

class GuessNumberGameClient:
    def __init__(self, host, port):
        channel = grpc.insecure_channel(f"{host}:{port}")
        self.stub = guess_number_game_pb2_grpc.GuessNumberGameStub(channel)
        self.latch = futures.Future()

    def hasWinner(self, response):
        winner = response.winner
        return winner if winner != 0 else -1

    def start_game(self):
        def handle_response(response):
            print(response.message)
            winner = self.hasWinner(response)
            if winner >= 0:
                print(f"Player {winner} wins!")
                self.latch.set_result(True)

        request_iterator = self.generate_requests()
        response_iterator = self.stub.GuessNumber(request_iterator)

        try:
            for response in response_iterator:
                handle_response(response)
                if self.latch.done():
                    break
        except grpc.RpcError:
            print("The Game Has been Ended")

    def generate_requests(self):
        while True:
            try:
                number = int(input("Enter your guess (between 1 and 1000): "))
                yield guess_number_game_pb2.GuessRequest(number=number)
            except Exception as e:
                print("Error: " + str(e))
                sys.exit(1)

    def shutdown(self):
        pass


if __name__ == "__main__":
    host = "localhost"
    port = 9000

    client = GuessNumberGameClient(host, port)

    try:
        client.start_game()
    except KeyboardInterrupt:
        pass
    finally:
        client.shutdown()


