package com.example.hotel_management.Service;
import com.lowagie.text.DocumentException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Service
@AllArgsConstructor
public class GeneratePdfService {

        private JavaMailSender javaMailSender;
        private TemplateEngine thymeleafTemplateEngine;

        public void sendEmail( String to ,String body ,String subject){
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom("hoteldyafatocom@gmail.com");
            simpleMailMessage.setTo(to);
            simpleMailMessage.setText(body);
            simpleMailMessage.setSubject(subject);
            javaMailSender.send(simpleMailMessage);
            System.out.println("email send");


        }

        public void sendEmailWithAttachment(String to, String body, String subject, String attachmentName,Context context)
                throws MessagingException, DocumentException, IOException {
            // Créer le contenu HTML à partir du modèle Thymeleaf

            String htmlContent = thymeleafTemplateEngine.process("toPdfFile", context);

            // Créer le PDF à partir du contenu HTML
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            renderer.finishPDF();

            // Convertir le flux de sortie en tableau d'octets
            byte[] pdfContents = outputStream.toByteArray();


            // Créer une ressource ByteArray pour le fichier PDF
            ByteArrayResource file = new ByteArrayResource(pdfContents);

            // Créer le message e-mail avec pièce jointe
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("hoteldyafatocom@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            // Ajouter la pièce jointe PDF
            helper.addAttachment(attachmentName+".pdf", file);


            // Envoyer l'e-mail
            javaMailSender.send(message);
            System.out.println("E-mail envoyé avec succès !");
        }
}

