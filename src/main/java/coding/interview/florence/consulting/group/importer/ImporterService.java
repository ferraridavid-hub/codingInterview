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

    public UserEntity importUsers(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            CSVReader csvReader = new CSVReader(fileReader);
            String[] line;
            csvReader.readNext();   // skip header
            while((line = csvReader.readNext()) != null) {
                UserEntity userToBePersisted = mapUserEntity(line);
                if (userToBePersisted != null) {
                    userRepository.save(userToBePersisted);
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("failed to parse CSV file: " + e.getMessage());
        }
        return null;
    }

    private UserEntity mapUserEntity(String[] csvLine) {
        if (csvLine.length < 2) {
            return null;
        }

        String name = csvLine[0].trim();
        String lastName = csvLine[1].trim();

        return new UserEntity(name, lastName);
    }
}
