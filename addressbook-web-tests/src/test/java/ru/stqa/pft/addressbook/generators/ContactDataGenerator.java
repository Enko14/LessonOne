package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-count", description = "Contact count")
  public int count;
  @Parameter(names = "-file", description = "File")
  public String file;
  @Parameter(names = "-format", description = "Format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException e) {
      jCommander.usage();
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contactData = generateContacts(count);
    if (format.equals("csv")) {
      System.out.println("USE CSV");
    } else if (format.equals("xml")) {
      System.out.println("USE XML");
    } else if (format.equals("json")) {
      saveAsJSON(contactData, new File(file));
    } else {
      System.out.println("Format not not not");
    }
  }

  private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contactData = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      contactData.add(new ContactData().withName(String.format("Aleksey %s", i)).withSurname(String.format("Meshchaninov %s", i)).withCity(String.format("Moscow %s", i)).withAddress2(String.format("Moscow %s", i)).withEmail(String.format("enkooo" + i + "@mail.ru")).withNickname(String.format("enkooo %s", i)).withCompany(String.format("InfoTeCS %s", i)).withEmail2(String.format("enkooo" + i + "@mail.ru")).withEmail3(String.format("enkooo" + i + "@mail.ru")));
    }
    return contactData;
  }
}
