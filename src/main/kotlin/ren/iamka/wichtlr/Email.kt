package ren.iamka.wichtlr

import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


private object MailConfig {
    const val HOST = "smtp.gmail.com" // For example
    const val SENDER_ADDRESS = "REPLACEME@fakeprovider.com"
    const val SENDER_NAME = "Secret Santa Central"
    // Create an app password when using 2-factor auth: https://myaccount.google.com/apppasswords
    const val USER = "REPLACEME"
    const val PASSWORD = "REPLACEME"
    const val MAIL_SUBJECT = "Your secret santa partner 2018 was determined"
    const val MAIL_TEXT = "Hohoho!\r\n\r\nThis year you have to get a gift for: \r\n%s\r\n Have fun!"

    val PROPERTIES: Properties = System.getProperties().apply {
        setProperty("mail.smtp.host", HOST)
        setProperty("mail.transport.protocol", "smtp")
        setProperty("mail.smtp.port", "587")
        setProperty("mail.smtp.auth", "true")
        setProperty("mail.smtp.starttls.enable", "true")
    }
}

private object MailAuthenticator : Authenticator() {
    override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(MailConfig.USER, MailConfig.PASSWORD)
    }
}

internal fun sendEmail(recipientAddress: String, partnerName: String){
    val emailText = MailConfig.MAIL_TEXT.format(partnerName)
    try {
        val message = MimeMessage(Session.getDefaultInstance(MailConfig.PROPERTIES, MailAuthenticator)).apply {
            setFrom(InternetAddress(MailConfig.SENDER_ADDRESS, MailConfig.SENDER_NAME))
            addRecipient(Message.RecipientType.TO,
                    InternetAddress(recipientAddress))
            subject = MailConfig.MAIL_SUBJECT
            setText(emailText)
        }

        Transport.send(message)
        println("Sent e-mail to $recipientAddress successfully.")
    } catch (mex: MessagingException) {
        println("Error sending e-mail to $recipientAddress")
        mex.printStackTrace()
    }
}