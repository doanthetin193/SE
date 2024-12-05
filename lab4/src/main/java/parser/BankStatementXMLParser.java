//package main.java.parser;
//
//import main.java.model.BankTransaction;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Unmarshaller;
//import java.io.StringReader;
//import java.util.List;
//
//public class BankStatementXMLParser implements BankStatementParser {
//    @Override
//    public BankTransaction parseFrom(String line) {
//        throw new UnsupportedOperationException("XML parsing not supported for single lines.");
//    }
//
//    @Override
//    public List<BankTransaction> parseLinesFrom(List<String> lines) {
//        try {
//            JAXBContext context = JAXBContext.newInstance(BankTransaction.class);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            return (List<BankTransaction>) unmarshaller.unmarshal(new StringReader(String.join("", lines)));
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to parse XML.", e);
//        }
//    }
//}
