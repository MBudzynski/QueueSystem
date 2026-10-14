from flask import Flask, request, jsonify
from flask_cors import CORS
import win32print
import win32api
import base64

app = Flask(__name__)
CORS(app, resources={r"/print": {"origins": "http://localhost:5173"}})

@app.route('/print', methods=['POST'])
def print_text():
    try:
        data = request.get_json()

        if not data:
            return jsonify({"error": "PrintError"}), 400

        print_data = base64.b64decode(data['printData'])

        output_path = "number.pdf"
        with open(output_path, "wb") as pdf_file:
            pdf_file.write(print_data)

        drukarka = win32print.GetDefaultPrinter()

        win32api.ShellExecute(0, "print", output_path, None, ".", 0)

        return jsonify({"message": "PrintDone"}), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(port=3001)