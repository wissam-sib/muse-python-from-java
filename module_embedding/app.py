from flask import Flask, request, jsonify, render_template
import tensorflow_hub as hub
import tensorflow_text
import json
import numpy as np

muse_model = hub.load("https://tfhub.dev/google/universal-sentence-encoder-multilingual/3")


class NumpyEncoder(json.JSONEncoder):
    """ Special json encoder for numpy types """
    def default(self, obj):
        if isinstance(obj, np.integer):
            return int(obj)
        elif isinstance(obj, np.floating):
            return float(obj)
        elif isinstance(obj, np.ndarray):
            return obj.tolist()
        return json.JSONEncoder.default(self, obj)

class TransformerEmbedder:

    def __init__(self, sentence_preprocessor=None):
        self.sentence_preprocessor = sentence_preprocessor

    def fit(self, texts):
        pass

    def transform(self, texts):
        if self.sentence_preprocessor is None:
            return muse_model(texts).numpy()
        else:
            return muse_model(self.sentence_preprocessor.preprocess(texts)).numpy()


app = Flask(__name__)

myembedder =  TransformerEmbedder()

@app.route('/api/embed', methods=['GET'])
def embed_sentence():
	question = request.args.get('question')
	if not question:
		return json.dumps("No input question"), 200, {'Content-Type': 'application/json'}
	else:
		return json.dumps(myembedder.transform([question]), cls=NumpyEncoder), 200, {'Content-Type': 'application/json'}
	
	
if __name__ == "__main__":
    app.run(debug=True)


