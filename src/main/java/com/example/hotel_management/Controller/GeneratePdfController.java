package com.example.hotel_management.Controller;


import com.example.hotel_management.Entity.Reservation;
import com.example.hotel_management.Service.GeneratePdfService;
import com.example.hotel_management.Service.ReservationService;
import com.lowagie.text.DocumentException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class GeneratePdfController {
    @Autowired
    private TemplateEngine thymeleafTemplateEngine;
    @Autowired
    private GeneratePdfService emailSendService;
    @Autowired
    private ReservationService reservationService;
    @GetMapping("/facture/{id}")
    public String getPdfFacture(@PathVariable Long id ,Model model){

        Reservation reservation=reservationService.getReservationById(id).get();
        model.addAttribute("reservation",reservation);

        return "toPdfFile";
    }

    @GetMapping("/sendmail/{id}")
    @ResponseBody
    public String sendMail(@PathVariable Long id) throws MessagingException, DocumentException, IOException {
        //emailSendService.sendEmail("moadelmaazouzi@gmail.com","hello moad ","java Email");


        Context context= new Context();
        Reservation reservation= reservationService.getReservationById(id).get();
        context.setVariable("reservation",reservation);
        String body="bonjour voissi votre facture de reservation distribuer par votre hotel reserver"+reservation.getChambre().getHotel().getNom();
        emailSendService.sendEmailWithAttachment(reservation.getClient().getEmail(),body,"facture","facture", context);
        return "message envoyer";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/generatePdf")
    public ResponseEntity<byte[]> generatePdf(HttpServletResponse response) throws IOException {
        try {
            Context context = new Context();
            context.setVariable("name", "John Doe");
            // Créer le contenu HTML et le convertir en PDF
            String htmlContent = thymeleafTemplateEngine.process("toPdfFile", context);;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            renderer.finishPDF();

            // Convertir le flux de sortie en tableau d'octets
            byte[] pdfContents = outputStream.toByteArray();
            // Configuration de l'en-tête pour le téléchargement du PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "generated.pdf");
            headers.setContentLength(pdfContents.length);

            // Retourner le PDF dans le corps de la réponse avec le code de statut 200 OK
            return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions selon vos besoins
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

