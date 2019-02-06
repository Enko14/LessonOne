package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GetIpLocation;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    //Нет объекта :( только в строку можно представить ответ
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("213.87.162.84");
    assertTrue(ipLocation.contains("RU"));
  }

  /**
   * Тест с невалидным IP ведет себя очень странно, поэтому я не стал его сюда добавлять
   * <GeoIP><Country>US</Country><State>CA</State></GeoIP> - то что возвращается, что бы я не передавал
   */
}
