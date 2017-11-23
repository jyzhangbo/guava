package cn.ennwifi.guava.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @author zhangbo
 *
 */
public class Handler implements Runnable {
  private static final Logger LOGGER = Logger.getLogger("Handler");

  public Socket socket;


  public Handler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    BufferedReader reader = null;
    try {
      LOGGER.info("client连接成功");
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String info = null;
      while ((info = reader.readLine()) != null) {
        LOGGER.info("获取到的数据：" + info);

        try {
          // 一定要捕获异常,并进行处理
          HashMap<String, Object> data = new HashMap<>();
          data.put("message", info);
        } catch (Exception e) {
          LOGGER.info("处理异常");
        }
      }
      reader.close();
      socket.close();
      LOGGER.info("client断开连接");
    } catch (Exception e) {
      LOGGER.info(e.getMessage());
    } finally {
      try {
        reader.close();
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }

}
