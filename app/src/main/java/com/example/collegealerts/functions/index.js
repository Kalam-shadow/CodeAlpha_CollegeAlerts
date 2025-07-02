/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

// const {setGlobalOptions} = require("firebase-functions");
// const {onRequest} = require("firebase-functions/https");
// const logger = require("firebase-functions/logger");

// For cost control, you can set the maximum number of containers that can be
// running at the same time. This helps mitigate the impact of unexpected
// traffic spikes by instead downgrading performance. This limit is a
// per-function limit. You can override the limit for each function using the
// `maxInstances` option in the function's options, e.g.
// `onRequest({ maxInstances: 5 }, (req, res) => { ... })`.
// NOTE: setGlobalOptions does not apply to functions using the v1 API. V1
// functions should each use functions.runWith({ maxInstances: 10 }) instead.
// In the v1 API, each function can only serve one request per container, so
// this will be the maximum concurrent request count.

// Create and deploy your first functions
// https://firebase.google.com/docs/functions/get-started

// exports.helloWorld = onRequest((request, response) => {
//   logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });

const {onValueCreated} = require("firebase-functions/v2/database");
const admin = require("firebase-admin");
admin.initializeApp();

exports.sendEventNotification = onValueCreated("/events/{eventId}", (event) => {
  const snapshot = event.data;
  if (!snapshot) {
    console.log("No data found");
    return null;
  }

  const eventData = snapshot.val();
  const payload = {
    notification: {
      title: "New Event: " + eventData.title,
      body: eventData.description,
    },
    topic: "allStudents",
  };

  return admin.messaging().send(payload)
      .then((response) => {
        console.log("Successfully sent message:", response);
        return null;
      })
      .catch((error) => {
        console.error("Error sending message:", error);
      });
});
