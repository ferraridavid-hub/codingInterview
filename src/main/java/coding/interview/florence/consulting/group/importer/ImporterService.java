package coding.interview.florence.consulting.group.importer;

import coding.interview.florence.consulting.group.user.UserEntity;
import coding.interview.florence.consulting.group.user.UserRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class ImporterService {

    @Autowired
    private UserRepository userRepository;

    public void importUsers(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        CSVReader csvReader = new CSVReader(fileReader)) {

            csvReader.readNext();   // skip header

            String[] line;
            while((line = csvReader.readNext()) != null) {
                UserEntity userToBePersisted = mapUserEntity(line);
                if (userToBePersisted != null) {
                    userRepository.save(userToBePersisted);
                }
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("failed to parse CSV file: " + e.getMessage());
        }
    }

    private UserEntity mapUserEntity(String[] csvLine) {
        if (csvLine.length < 3) {
            return null;
        }

        String name = csvLine[0].trim();
        String lastName = csvLine[1].trim();
        String email = csvLine[2].trim();

        return new UserEntity(name, lastName, email);
    }
}
