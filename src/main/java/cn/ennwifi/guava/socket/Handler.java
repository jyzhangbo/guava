package cn.ennwifi.guava.socket;

import java.io.BufferedReader;
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
    try {
      LOGGER.info("client连接成功");
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String info = null;
      while ((info = reader.readLine()) != null) {
        LOGGER.info("获取到的数据：" + info);
        HashMap<String, Object> data = new HashMap<>();
        data.put("message", info);
      }
      reader.close();
      LOGGER.info("client断开连接");
    } catch (Exception e) {
      LOGGER.info(e.getMessage());
    }
  }

}
