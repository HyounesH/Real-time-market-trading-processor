import {Injectable} from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import {environment} from 'src/environments/environment';

const SOCKET_ENDPOINT = environment.SOCKET_ENDPOINT;

@Injectable({
  providedIn: 'root'
})
export class SocketService {
  private connecting: boolean;
  private topicQueue: any[] = [];

  socket = new SockJS(SOCKET_ENDPOINT);
  stompClient = Stomp.over(this.socket);

  subscribe(topic: string, callback: any): void {
    if (this.connecting) {
      this.topicQueue.push({
        topic,
        callback
      });
      return;
    }

    const connected: boolean = this.stompClient.connected;
    if (connected) {
      // Once we are connected set connecting flag to false
      this.connecting = false;
      this.subscribeToTopic(topic, callback);
      return;
    }

    // If stomp client is not connected connect and subscribe to topic
    this.connecting = true;
    this.stompClient.connect({}, (): any => {
      this.subscribeToTopic(topic, callback);

      // Once we are connected loop the queue and subscribe to remaining topics from it
      this.topicQueue.forEach((item: any) => {
        this.subscribeToTopic(item.topic, item.callback);
      })

      // Once done empty the queue
      this.topicQueue = [];
    });
  }

  private subscribeToTopic(topic: string, callback: any): void {
    this.stompClient.subscribe(topic, (response?:string): any => {
      callback(response);
    });
  }
}
