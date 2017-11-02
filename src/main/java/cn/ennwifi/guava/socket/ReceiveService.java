package cn.ennwifi.guava.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * @author zhangbo
 *
 */
public class ReceiveService {
  private static final Logger LOGGER = Logger.getLogger("Receive-Service");

  private static ServerSocket socket;
  private static ExecutorService service;


  public static void main() {
    service = Executors.newFixedThreadPool(5);
    // 开始监听端口
    service.execute(() -> {
      while (true) {
        try {
          if (socket == null) {
            socket = new ServerSocket(9039);
            LOGGER.info("socket开始监听端口：" + 9039);
          }
        } catch (Exception e) {
          LOGGER.info("socket监听端口异常:" + e.getMessage());
        }
        receiveData();
      }
    });
  }

  /**
   * 获取数据.
   */
  public static void receiveData() {

    try {
      // 获取socket连接，没有则等待
      Socket accept = socket.accept();
      service.execute(new Handler(accept));
    } catch (Exception e) {
      LOGGER.info(e.getMessage());
    }
  }

}
